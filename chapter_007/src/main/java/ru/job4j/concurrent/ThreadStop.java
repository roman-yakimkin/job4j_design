package ru.job4j.concurrent;

/**
 * Класс для демонстрации прерывания нити
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 31.05.2020
 * @version 1.0
 */
public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        Thread.sleep(2);
        thread.interrupt();
    }
}
