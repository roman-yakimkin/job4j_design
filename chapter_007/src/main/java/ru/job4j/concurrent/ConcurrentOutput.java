package ru.job4j.concurrent;

/**
 * Демонстнация нити исполнения
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 29.05.2020
 * @version 1.0
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName()),
                "Another thread"
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName()),
                "Second thread"
        );
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
