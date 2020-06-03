package ru.job4j.concurrent;

/**
 * Класс, блокирующий выполненеи по условию счетчика
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 03.06.2020
 * @version 1.0
 */
public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            if (count == total) {
                monitor.notifyAll();
            }
            count++;
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count != total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
