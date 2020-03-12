package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

/**
 * Тестовый класс для поиска максимального и минимального значения
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 1.0
 * @version 1.0
 */
public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (T element : value) {
            if (comparator.compare(element, result) > 0) {
                result = element;
            }
        }
        return result;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (T element : value) {
            if (comparator.compare(element, result) < 0) {
                result = element;
            }
        }
        return result;
    }
}
