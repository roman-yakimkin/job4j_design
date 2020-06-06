package ru.job4j.cas;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASCounterTest {

    @Test
    public void incCounterInThreeThreads() throws InterruptedException {
        CASCounter counter = new CASCounter();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    counter.increment();
                    Thread.sleep(10);
                    counter.increment();
                    Thread.sleep(10);
                    counter.increment();
                    Thread.sleep(10);
                    counter.increment();
                    Thread.sleep(10);
                    counter.increment();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);
        Thread t5 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        assertThat(counter.get(), is(25));
    }
}
