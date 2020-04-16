package ru.job4j.design.menu;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Класс - реализация меню
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.04.2020
 * @version 1.0
 */
public class ConsoleMenu implements Menu {
    private List<MenuItem> items;

    public ConsoleMenu(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public boolean add(MenuItem parent, MenuItem newItem) {
        newItem.setParent(parent);
        return items.add(newItem);
    }

    @Override
    public boolean remove(MenuItem item) {
        return items.remove(item);
    }

    @Override
    public List<MenuItem> filterMenuItems(Predicate<MenuItem> condition) {
        return items.stream().filter(condition).collect(Collectors.toList());
    }
}
