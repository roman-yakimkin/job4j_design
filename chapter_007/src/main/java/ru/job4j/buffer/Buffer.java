package ru.job4j.buffer;

/**
 * Класс для демонстрации монитора и критической секции
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.06.2020
 * @version 1.0
 */
public class Buffer {
    private StringBuilder buffer = new StringBuilder();

    public synchronized void add(int value) {
        System.out.print(value);
        buffer.append(value);
    }

    @Override
    public synchronized String toString() {
        return buffer.toString();
    }
}
