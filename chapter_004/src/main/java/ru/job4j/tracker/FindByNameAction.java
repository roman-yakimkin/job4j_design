package ru.job4j.tracker;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * Действие - поиск заявки по имени
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.02.2020
 * @version 1.0
 */
@Component
public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by name ===";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String name = input.askStr("Input items' name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item: items) {
                output.accept("ID: " + item.getId() + ", name: " + item.getName());
            }
        } else {
            output.accept("No items");
        }
        return true;
    }
}
