package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Действие - поиск по id заявки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.02.2020
 * @version 1.0
 */
public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by id ===";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String id = input.askStr("Input item's id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            output.accept("ID: " + item.getId() + ", name: " + item.getName());
        }
        return true;
    }
}
