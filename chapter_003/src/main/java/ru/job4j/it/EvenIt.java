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
        return Arrays.stream(Arrays.copyOfRange(data, point, data.length)).anyMatch((el) -> (el % 2 == 0));
    }

    @Override
    public Integer next() {
        Integer result = null;
        do {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (data[point] % 2 == 0) {
                result = data[point];
            }
            point++;
        } while (result == null);
        return result;
    }
}
