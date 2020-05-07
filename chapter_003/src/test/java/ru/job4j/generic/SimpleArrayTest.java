package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {
    @Test
    public void addElementTest() {
        SimpleArray<String> sa = new SimpleArray<String>(3);
        sa.add("first");
        assertThat(sa.get(0), is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addElementsAndOutOfBoundsTest() {
        SimpleArray<String> sa = new SimpleArray<String>(1);
        sa.add("first");
        sa.add("second");
        assertThat(sa.get(0), is("first"));
    }

    @Test
    public void removeElementTest() {
        SimpleArray<String> sa = new SimpleArray<String>(3);
        sa.add("first");
        sa.add("second");
        sa.remove(0);
        assertThat(sa.get(0), is("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeElementAndOutOfBoundTest() {
        SimpleArray<String> sa = new SimpleArray<String>(3);
        sa.add("first");
        sa.add("second");
        sa.remove(2);
    }

    @Test
    public void setElementTest() {
        SimpleArray<String> sa = new SimpleArray<String>(3);
        sa.add("first");
        sa.set(0, "new first");
        assertThat(sa.get(0), is("new first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setElementAndOutOfBoundsTest() {
        SimpleArray<String> sa = new SimpleArray<String>(3);
        sa.set(2, "new element");
    }

    @Test
    public void iteratorTest() {
        SimpleArray<Integer> sa = new SimpleArray<>(5);
        sa.add(1);
        sa.add(4);
        sa.add(9);
        sa.add(16);
        sa.add(25);
        Iterator<Integer> it = sa.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(16));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(25));
        assertThat(it.hasNext(), is(false));
    }
}
