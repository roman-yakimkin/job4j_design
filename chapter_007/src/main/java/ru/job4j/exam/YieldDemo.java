package ru.job4j.exam;

/**
 * Класс для демонстрации метода yield
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 09.06.2020
 * @version 1.0
 */
public class YieldDemo implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " уступает работу другим потокам");
        Thread.yield();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " завершает работу");
    }

    public static void main(String[] args) {
        Runnable yd = new YieldDemo();
        new Thread(yd, "Thread 1").start();
        new Thread(yd, "Thread 2").start();
        new Thread(yd, "Thread 3").start();
    }
}
