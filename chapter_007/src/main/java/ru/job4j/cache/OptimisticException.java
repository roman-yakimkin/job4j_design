package ru.job4j.cache;

/**
 * Класс - пользовательское исключение
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 06.06.2020
 * @version 1.0
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
