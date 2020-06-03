package ru.job4j.concurrent;

/**
 * Класс для демонстрации методов wait и notifyAll
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 03.06.2020
 * @version 1.0
 */
public class Barrier {
    private boolean flag = false;

    private final Object monitor = this;

    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
