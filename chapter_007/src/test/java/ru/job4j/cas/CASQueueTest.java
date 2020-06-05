package ru.job4j.cas;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASQueueTest {
    @Test
    public void whenPushAndPullInSingleMode() {
        CASQueue<Integer> queue = new CASQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(1, is(queue.poll()));
        assertThat(2, is(queue.poll()));
        assertThat(3, is(queue.poll()));
    }

    @Test
    public void whenPushWithTwoThreads() throws InterruptedException {
        CASQueue<Integer> queue = new CASQueue<>();
        Thread pushFirst = new Thread(
                () -> {
                    try {
                        queue.push(1);
                        Thread.sleep(100);
                        queue.push(2);
                        Thread.sleep(100);
                        queue.push(3);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread pushSecond = new Thread(
                () -> {
                    try {
                        queue.push(4);
                        Thread.sleep(100);
                        queue.push(5);
                        Thread.sleep(100);
                        queue.push(6);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
        pushFirst.start();
        pushSecond.start();
        pushFirst.join();
        pushSecond.join();

        Set<Integer> result = new HashSet<>();
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        assertThat(result, is(Set.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    public void whenPullWithTwoThreads() throws InterruptedException {
        CASQueue<Integer> queue = new CASQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);

        AtomicIntegerArray pullArray = new AtomicIntegerArray(6);
        AtomicInteger pullArrayIndex = new AtomicInteger(0);

        Thread pullFirst = new Thread(
                () -> {
                    try {
                        synchronized (queue) {
                            pullArray.set(pullArrayIndex.getAndIncrement(), queue.poll());
                        }
                        System.out.println("first 1 - " + pullArray.toString());
                        Thread.sleep(100);

                        synchronized (queue) {
                            pullArray.set(pullArrayIndex.getAndIncrement(), queue.poll());
                        }
                        System.out.println("first 2- " + pullArray.toString());
                        Thread.sleep(100);

                        synchronized (queue) {
                            pullArray.set(pullArrayIndex.getAndIncrement(), queue.poll());
                        }
                        System.out.println("first 3 - " + pullArray.toString());
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread pullSecond = new Thread(
                () -> {
                    try {
                        synchronized (queue) {
                            pullArray.set(pullArrayIndex.getAndIncrement(), queue.poll());
                        }
                        System.out.println("second 1 - " + pullArray.toString());
                        Thread.sleep(100);

                        synchronized (queue) {
                            pullArray.set(pullArrayIndex.getAndIncrement(), queue.poll());
                        }
                        System.out.println("second 2 - " + pullArray.toString());
                        Thread.sleep(100);

                        synchronized (queue) {
                            pullArray.set(pullArrayIndex.getAndIncrement(), queue.poll());
                        }
                        System.out.println("second 3 - " + pullArray.toString());
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        pullFirst.start();
        pullSecond.start();
        pullFirst.join();
        pullSecond.join();

        int[] result = new int[6];
        for (int i = 0; i < 6; i++) {
            result[i] = pullArray.get(i);
        }
        Arrays.sort(result);
        assertThat(result, is(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
