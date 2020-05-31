package ru.job4j.concurrent;

import java.awt.*;

/**
 * Класс для симуляции процесса загрузки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.05.2020
 * @version 1.0
 */
public class Wget {
    public static void main(String[] args) {
        new Thread(
                () -> {
                    try {
                        for (int i = 1; i <= 100; i++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + i  + "%");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        ).start();
    }
}
