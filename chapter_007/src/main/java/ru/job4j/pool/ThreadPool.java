package ru.job4j.pool;

import ru.job4j.synch.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс - многопоточный пул
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.06.2020
 * @version 1.0
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread worker = new Worker(tasks);
            threads.add(worker);
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    static class Worker extends Thread {

        private final SimpleBlockingQueue<Runnable> tasks;

        private Worker(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable job;
                try {
                    job = tasks.poll();
                    job.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
