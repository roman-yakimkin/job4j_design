package ru.job4j.it;

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
            private Iterator<Integer> currentIterator = null;
            private boolean isConvertedInitialed = false;

            private void initConverter() {
                while (it.hasNext()) {
                    currentIterator = it.next();
                    if (currentIterator.hasNext()) {
                        break;
                    }
                }
                isConvertedInitialed = true;
            }

            @Override
            public boolean hasNext() {
                if (!isConvertedInitialed) {
                    initConverter();
                }
                return it.hasNext() || currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!isConvertedInitialed) {
                    initConverter();
                }
                Integer result = null;
                do {
                    if (currentIterator == null || !currentIterator.hasNext()) {
                        if (!it.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        currentIterator = it.next();
                    } else {
                        result = currentIterator.next();
                    }
                } while (result == null);
                return result;
            }
        };
    }
}
