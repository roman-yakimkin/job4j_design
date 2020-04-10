package ru.job4j.design.menu;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс для меню
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.04.2020
 * @version 1.0
 */
public interface Menu {
    public boolean add(MenuItem parent, MenuItem newItem);
    public boolean remove(MenuItem item);
    public List<MenuItem> filterMenuItems(Predicate<MenuItem> condition);
}
