package ru.job4j.concurrent;

/**
 * Класс для демонстрации прерывания нити
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 31.05.2020
 * @version 1.0
 */
public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] symbols = new char[] {'-', '\\', '|', '/'};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            System.out.print("\r load: " + symbols[index++]);
            if (index == symbols.length) {
                index = 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
