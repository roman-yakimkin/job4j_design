package ru.job4j.tracker;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Действие - поиск по id заявки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.02.2020
 * @version 1.0
 */
@Component
public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by id ===";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String id = input.askStr("Input item's id: ");
        try {
            Item item = tracker.findById(Integer.parseInt(id));
            if (item != null) {
                output.accept("ID: " + item.getId() + ", name: " + item.getName());
            }
        } catch (NumberFormatException e) {
            output.accept("You should input a numeric value");
        }
        return true;
    }
}
