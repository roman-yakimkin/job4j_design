package ru.job4j.concurrent;

/**
 * Класс для демонстрации синхронизации
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 01.06.2020
 * @version 1.0
 */
public class Cache {
    private static Cache cache;

    private synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
