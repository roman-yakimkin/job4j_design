package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Действие - выход из программы
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.02.2020
 * @version 1.0
 */
public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "=== Goodbye ===";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        return false;
    }
}
