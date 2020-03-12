package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Тестовый класс для поиска максимального и минимального значения
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 1.0
 * @version 1.0
 */
public class MaxMin {

    /**
     * Универсальная функция для поиска минимального и максимального значений
     * @param value - список для поиска
     * @param comparator - компаратор
     * @param signExpected - ожидаемый знак компаратора
     * @param <T> - результат
     * @return
     */
    public <T> T findElem(List<T> value, Comparator<T> comparator, Integer signExpected) {
        T result = value.get(0);
        for (T element : value) {
            if ( Integer.signum(comparator.compare(element, result)) == signExpected ) {
                result = element;
            }
        }
        return result;
    }

    /**
     * Поиск максимального значения
     * @param value - список для поиска
     * @param comparator - компаратор
     * @param <T> - ожидаемый знак компаратора
     * @return - максимальное значение
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findElem(value, comparator, 1);
    }

    /**
     * Поиск мимимального значения
     * @param value - список для поиска
     * @param comparator - компаратор
     * @param <T> - ожидаемый знак компаратора
     * @return - минимальное значение
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findElem(value, comparator, -1);
    }
}