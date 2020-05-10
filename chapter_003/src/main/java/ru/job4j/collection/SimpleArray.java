package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация массива, аналогичного ArrayList
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 08.05.2020
 * @version 1.0
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int index = 0;
    private int modCount = 0;

    public SimpleArray(int size) {
        container = new Object[size];
    }

    public SimpleArray() {
        container = new Object[1];
    }

    private void expandContainer() {
        Object[] newContainer = new Object[container.length * 2];
        System.arraycopy(container, 0, newContainer, 0, container.length);
        container = newContainer;
    }

    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return (T) container[index];
    }

    public void add(T model) {
        if (index == container.length - 1) {
            expandContainer();
        }
        container[index++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pointer = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return pointer < index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[pointer++];
            }
        };
    }
}
