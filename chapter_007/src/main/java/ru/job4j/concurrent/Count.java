package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс - счетчик для демонстрации многопоточного тестирования
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.06.2020
 * @version 1.0
 */

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}
