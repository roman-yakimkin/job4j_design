package ru.job4j.concurrent;

/**
 * Класс для демонстрации приостановки работы нити
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.05.2020
 * @version 1.0
 */
public class ThreadSleep {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        Thread.sleep(3000);
                        System.out.println("Loaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
