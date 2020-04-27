package ru.job4j.io;

/**
 * Класс для работы с аргументами архива
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 27.04.2020
 * @version 1.0
 */
public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        return false;
    }

    public String directory() {
        return null;
    }

    public String exclude() {
        return null;
    }

    public String output() {
        return null;
    }
}
