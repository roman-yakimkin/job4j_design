package ru.job4j.cache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CacheTest {
    @Test
    public void whenExceptionThrows() throws InterruptedException {
        Cache cache = new Cache();
        Base model = new Base(1, 1);
        cache.add(model);
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread t1 = new Thread(
                () -> {
                    try {
                        cache.update(model);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, 1));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(ex.get().getMessage(), is("The version has been changed"));
    }
}
