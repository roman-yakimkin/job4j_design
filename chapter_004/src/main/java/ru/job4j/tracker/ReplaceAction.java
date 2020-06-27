package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Действие - замена заявки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.02.2020
 * @version 1.0
 */
public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit item ===";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String id = input.askStr("Input item's id: ");
        String name = input.askStr("Input item's name name: ");
        if (tracker.replace(Integer.parseInt(id), new Item(Integer.parseInt(id), name))) {
            output.accept("The item with id: " + id + " has been replaced with new name: " + name);
        } else {
            output.accept("Unable to replace the item with id: " + id);
        }
        return true;
    }
}
