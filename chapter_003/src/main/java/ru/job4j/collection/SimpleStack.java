package ru.job4j.collection;

/**
 * Простой стек на базе односвязного списка
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 08.05.2020
 * @version 1.0
 */
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }

    public T pop() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }
}
