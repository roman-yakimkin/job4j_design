package ru.job4j.concurrent;

/**
 * Класс для демонстрации состояния нити
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 29.05.2020
 * @version 1.0
 */
public class ThreadState {

    private static void printThreadState(Thread thread) {
        System.out.format("%s -> %s\n", thread.getName(), thread.getState());
    }

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }, "First thread"
        );
        Thread second = new Thread(
                () -> { }, "Second thread"
        );
        printThreadState(first);
        printThreadState(second);
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            printThreadState(first);
            printThreadState(second);
        }
        printThreadState(first);
        printThreadState(second);
        System.out.println("The program has finished");
    }
}
