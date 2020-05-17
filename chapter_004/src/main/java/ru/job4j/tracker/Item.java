package ru.job4j.tracker;

import java.util.Objects;

/**
 * Класс - заявка
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 26.01.2020
 * @version 1.0
 */
public class Item {

    /**
     * идентификатор заявки
     */
    private String id;

    /**
     * Название заявки
     */
    private String name;

    /**
     * Конструктор заявки
     * @param name - название заявки
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Конструктор заявки по её идентификатору и имени
     * @param id - идентификатор заявки
     * @param name - имя заявки
     */
    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Конструктор заявки по другой заявке
     * @param item - исходная заявка
     */
    public Item(Item item) {
        this.id = item.getId();
        this.name = item.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return getId().equals(item.getId())
                && getName().equals(item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
