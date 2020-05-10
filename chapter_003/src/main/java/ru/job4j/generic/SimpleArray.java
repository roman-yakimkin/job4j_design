package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Простой массив, реализованный на обобщениям с поддержкой итератора
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.05.2020
 * @version 1.0
 */
public class SimpleArray<T> implements Iterable<T> {
    private final T[] data;
    private int index = 0;

    public SimpleArray(int size) {
        data = (T[]) new Object[size];
    }

    /**
     * Добавить элемент в массив
     * @param model - добавляемый элемент
     */
    public void add(T model) {
        data[index++] = model;
    }

    /**
     * Удалить элемент из массива по индексу
     * @param index - индекс удаляемого элемента
     * @return - удаленный элемент
     */
    public T remove(int index) {
        Objects.checkIndex(index, this.index);
        T result = get(index);
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        data[this.index - 1] = null;
        this.index--;
        return result;
    }

    /**
     * Установить элемент по индексу
     * @param index - индекс
     * @param model - устанавливаемый элемент
     */
    public void set(int index, T model) {
        Objects.checkIndex(index, this.index);
        data[index] = model;
    }

    /**
     * Получить элемент по индексу
     * @param index - индекс
     * @return - полученный элемент
     */
    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return data[index];
    }

    /**
     * Получить итератор для массива
     * @return - полученный итератор для массива
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pointer = 0;
            @Override
            public boolean hasNext() {
                return pointer < data.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[pointer++];
            }
        };
    }
}
