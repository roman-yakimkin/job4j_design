package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор, реализующий четные числа
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 06.05.2020
 * @version 1.0
 */
public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (!result && point < data.length) {
            if (data[point] % 2 == 0) {
                result = true;
            } else {
                point++;
            }
        }

        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
