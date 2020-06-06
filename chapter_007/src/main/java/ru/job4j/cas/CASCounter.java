package ru.job4j.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Счетчик, построенный на неблокирующих операциях
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 06.06.2020
 * @version 1.0
 */
public class CASCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public int get() {
        return counter.get();
    }

    public void increment() {
        int v;
        do {
            v = counter.get();
        } while (!counter.compareAndSet(v, v + 1));
    }
}
