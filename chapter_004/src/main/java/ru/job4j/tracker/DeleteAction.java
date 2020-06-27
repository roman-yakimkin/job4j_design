package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Действие - удаление заявки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.02.2020
 * @version 1.0
 */
public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String id = input.askStr("Input item's id: ");
        if (tracker.delete(Integer.parseInt(id))) {
            output.accept("The item with id: " + id + " has been deleted");
        } else {
            output.accept("Unable to delete the item with id: " + id);
        }
        return true;
    }
}
