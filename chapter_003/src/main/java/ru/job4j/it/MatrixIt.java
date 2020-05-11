package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация итератора для двумерного массива
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 06.05.2020
 * @version 1.0
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        return (row < data.length);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = data[row][column++];
        if (column > data[row].length - 1) {
            row++;
            column = 0;
        }
        return result;
    }
}
