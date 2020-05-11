package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ForwardLinkedTest {
    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenAddAndRevertNonRecursiveThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        linked.revertNonRecursive();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenRevertEmptyList() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenRevertNonRecursiveEmptyList() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.revertNonRecursive();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.hasNext(), is(false));
    }
}
