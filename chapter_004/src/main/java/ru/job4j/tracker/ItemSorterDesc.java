package ru.job4j.tracker;

import java.util.Comparator;

/**
 * Компаратор для реализации сортировки заявок по убыванию
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.02.2020
 * @version 1.0
 */
public class ItemSorterDesc implements Comparator<Item> {
    @Override
    public int compare(Item first, Item second) {
        return second.getName().compareTo(first.getName());
    }
}
