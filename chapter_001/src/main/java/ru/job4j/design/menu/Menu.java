package ru.job4j.design.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Класс - реализация меню
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.04.2020
 * @version 1.0
 */
public class Menu implements IMenu {
    List<IMenuItem> items;

    public Menu(List<IMenuItem> items) {
        this.items = items;
    }

    @Override
    public boolean add(IMenuItem parent, IMenuItem newItem) {
        newItem.setParent(parent);
        return items.add(newItem);
    }

    @Override
    public boolean remove(IMenuItem item) {
        return items.remove(item);
    }

    @Override
    public List<IMenuItem> filterMenuItems(Predicate<IMenuItem> condition) {
        return items.stream().filter(condition).collect(Collectors.toList());
    }
}
