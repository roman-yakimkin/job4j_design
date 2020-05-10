package ru.job4j.collection;

import java.util.Iterator;

/**
 * Класс - простое множество на базе массива
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 09.05.2020
 * @version 1.0
 * @param <T> - тип данных, хранящихся в множестве
 */
public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> container;

    public SimpleSet() {
        container = new SimpleArray<>();
    }

    /**
     * Содержится ли значение в множестве
     * @param value - значение
     * @return - истина, если содержит
     */
    public boolean contains(T value) {
        for (T t : container) {
            if (t.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Добавить элемент в множество
     * @param value - добавляемый элемент
     */
    public void add(T value) {
        if (!contains(value)) {
            container.add(value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
