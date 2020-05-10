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
        for ( ; point < data.length; point++) {
            if (data[point] % 2 == 0) {
                break;
            }
        }
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
