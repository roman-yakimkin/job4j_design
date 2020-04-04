package ru.job4j.design.menu;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс для меню
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.04.2020
 * @version 1.0
 */
public interface IMenu {
    public boolean addMenuItem(IMenuItem parent, IMenuItem newItem);
    public boolean removeMenuItem(IMenuItem item);
    public List<IMenuItem> filterMenuItems(Predicate<IMenuItem> condition);
}
