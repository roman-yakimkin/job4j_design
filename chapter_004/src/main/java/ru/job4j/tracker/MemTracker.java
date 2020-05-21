package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс - трекер заявок
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 26.01.2020
 * @version 2.0
 */
public class MemTracker {

    /**
     * Массив для хранения заявок
     */
    private final List<Item> items = new ArrayList<Item>();

    /**
     * Генерация уникального ключа для заявки
     * @return сгенерированный уникальный ключ
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Добавить заявку в хранилище
     * @param item - добавляемая заявка
     * @return - добавленная заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Получть все заявки
     * @return массив заявок
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Получить заявку по её идентификатору
     * @param id - идентификатор заявки
     * @return - возвращаемая заявка
     */
    public Item findById(String id) {
        int index = indexOf(id);
        return (index == -1) ? null : this.items.get(index);
    }

    /**
     * Получить список заявок по их имени
     * @param name - имя заявки
     * @return - список заявок
     */
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<Item>();
        for (Item item : this.items) {
            if (item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Получить индекс заявки по её идентификатору
     * @param id - идентификатор заявки
     * @return - индекс заявки
     */
    private int indexOf(String id) {
        int result = -1;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = items.indexOf(item);
                break;
            }
        }
        return result;
    }

    /**
     * Замена заявки
     * @param id - id заявки
     * @param item - новая заявка
     * @return - истина, если произошла замена
     */
    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        boolean result = false;
        if (index != -1) {
           item.setId(id);
           this.items.set(index, item);
           result = true;
        }
        return result;
    }

    /**
     * Удалить заявку
     * @param id - id заявки
     * @return истина, если произошло удаление
     */
    public boolean delete(String id) {
        int index = indexOf(id);
        boolean result = false;
        if (index != -1) {
            this.items.remove(index);
            result = true;
        }
        return result;
    }
}
