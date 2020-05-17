package ru.job4j.tracker;

import java.util.Comparator;

/**
 * Компаратор для реализации сортировки заявок по возрастанию
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.02.2020
 * @version 1.0
 */
public class ItemSorterAsc implements Comparator<Item> {
    @Override
    public int compare(Item first, Item second) {
        return first.getName().compareTo(second.getName());
    }
}
