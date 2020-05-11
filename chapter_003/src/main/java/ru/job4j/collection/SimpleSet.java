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
    SimpleArray<T> container;

    public SimpleSet() {
        container = new SimpleArray<>();
    }

    public void add(T value) {
        Iterator<T> it = container.iterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
                return;
            }
        }
        container.add(value);
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
