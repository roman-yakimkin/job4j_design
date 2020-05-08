package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleListTest {
    @Test
    public void addElementAndGetIt() {
        SimpleList<String> sl = new SimpleList<>();
        sl.add("first");
        sl.add("second");
        sl.add("third");
        assertThat(sl.get(0), is("first"));
        assertThat(sl.get(1), is("second"));
        assertThat(sl.get(2), is("third"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenOneTriesToGetValueFromEmptyList() {
        SimpleList<String> sl = new SimpleList<>();
        sl.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenOneTriesToGetValueFromIndexOufOfBound() {
        SimpleList<String> sl = new SimpleList<>();
        sl.add("first");
        sl.add("second");
        sl.add("third");
        sl.get(5);
    }

    @Test
    public void testIterator() {
        SimpleList<String> sl = new SimpleList<>();
        sl.add("first");
        sl.add("second");
        sl.add("third");
        Iterator<String> it = sl.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("second"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("third"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorOnEmptyList() {
        SimpleList<String> sl = new SimpleList<>();
        Iterator<String> it = sl.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenOneModifiesTheListAfterCreationOfIterator() {
        SimpleList<String> sl = new SimpleList<>();
        sl.add("first");
        Iterator<String> it = sl.iterator();
        sl.add("second");
        it.next();
    }
}
