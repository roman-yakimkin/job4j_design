package ru.job4j.multithreads;

/**
 * Демонстрация печати двумя потоками
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 08.06.2020
 * @version 1.0
 */
public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier(false, true);
        Thread first = new Thread(
                () -> {
                    while (true) {
                        barrier.tryMaster();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread A");
                        barrier.doneSlave();
                    }
                }, "Thread A"
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        barrier.trySlave();
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.doneMaster();
                    }
                }, "Thread B"
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}