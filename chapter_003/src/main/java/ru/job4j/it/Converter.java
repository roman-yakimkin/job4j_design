package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс для конвертации итераторов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 06.05.2020
 * @version 1.0
 */
public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> currentIterator = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                boolean result = false;
                while (!result && (it.hasNext() || currentIterator.hasNext())) {
                    if (currentIterator.hasNext()) {
                        result = true;
                    } else {
                        currentIterator = it.next();
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }
        };
    }
}