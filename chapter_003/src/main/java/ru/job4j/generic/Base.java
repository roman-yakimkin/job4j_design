package ru.job4j.generic;

/**
 * Абстрактный класс базовой модели
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.05.2020
 * @version 1.0
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
