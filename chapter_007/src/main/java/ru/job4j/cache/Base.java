package ru.job4j.cache;

/**
 * Данные для кеширования
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 06.06.2020
 * @version 1.0
 */
public class Base {
    int id;
    int version;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }
}
