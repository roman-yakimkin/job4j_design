package ru.job4j.tracker;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Действие - создать заявку
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.02.2020
 * @version 1.0
 */
@Component
public class CreateAction implements UserAction {

    @Override
    public String name() {
        return "=== Create a new Item ===";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String name = input.askStr("Input item's name ");
        Item item = new Item(name);
        tracker.add(item);
        return true;
    }
}
