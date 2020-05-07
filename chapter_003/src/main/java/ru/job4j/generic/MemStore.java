package ru.job4j.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс - хранилище в памяти
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.05.2020
 * @version 1.0
 * @param <T> Типа хранимого объекта
 */
public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int index = 0; index < mem.size(); index++) {
            if (mem.get(index).getId().equals(id)) {
                mem.set(index, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index < mem.size(); index++) {
            if (mem.get(index).getId().equals(id)) {
                mem.remove(index);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        return mem.stream().filter((el) -> (el.getId().equals(id))).findFirst().orElse(null);
    }
}
