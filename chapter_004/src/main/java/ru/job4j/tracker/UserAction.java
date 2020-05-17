package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Интерфейс действий пользователя
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.02.2020
 * @version 1.0
 */
public interface UserAction {
    String name();
    boolean execute(Input input, Store tracker, Consumer<String> output);
}
