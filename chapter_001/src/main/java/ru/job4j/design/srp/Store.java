package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс для хранилища данных
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 04.04.2020
 * @version 1.0
 */
public interface Store {
        List<Employee> findBy(Predicate<Employee> filter);
}
