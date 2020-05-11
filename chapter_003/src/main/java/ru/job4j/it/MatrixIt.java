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
        return Arrays.stream(Arrays.copyOfRange(data, row, data.length)).anyMatch((el) -> el.length > 0);
    }

    @Override
    public Integer next() {
        Integer result = null;
        do {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (column < data[row].length) {
                result = data[row][column++];
            }
            if (column > data[row].length - 1) {
                row++;
                column = 0;
            }
        } while (result == null);
        return result;
    }
}
