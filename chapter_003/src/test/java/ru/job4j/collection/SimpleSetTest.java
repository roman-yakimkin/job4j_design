package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    @Test
    public void equalValuesTest() {
        SimpleSet<Integer> ss = new SimpleSet<>();
        ss.add(1);
        ss.add(2);
        ss.add(3);
        ss.add(2);
        ss.add(1);
        Iterator<Integer> it = ss.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }
}
