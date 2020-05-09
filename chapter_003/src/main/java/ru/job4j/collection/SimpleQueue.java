package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс - очередь на базе двух стеков
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 08.05.2020
 * @version 1.0
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private void moveData(SimpleStack<T> from, SimpleStack<T> to) {
        while (true) {
            try {
                to.push(from.pop());
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    public T poll() {
        moveData(in, out);
        T result = out.pop();
        moveData(out, in);
        return result;
    }

    public void push(T value) {
        in.push(value);
    }
}
