package ru.job4j.stat;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {
    @Test
    public void testAddElements() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "Иванов"),
                new Analyze.User(2, "Петров"),
                new Analyze.User(3, "Семенов"),
                new Analyze.User(4, "Федотов"),
                new Analyze.User(5, "Степаненко")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "Иванов"),
                new Analyze.User(2, "Петров"),
                new Analyze.User(3, "Семенов"),
                new Analyze.User(5, "Степанов"),
                new Analyze.User(6, "Васильев")
        );
        Analyze analyze = new Analyze();
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(1));
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(1));
    }

    @Test
    public void testAddNewElementsToEmptyList() {
        List<Analyze.User> previous = List.of();
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "Иванов"),
                new Analyze.User(2, "Петров"),
                new Analyze.User(3, "Семенов"),
                new Analyze.User(5, "Степанов"),
                new Analyze.User(6, "Васильев")
        );
        Analyze analyze = new Analyze();
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(5));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void testRemoveAllItemFromList() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "Иванов"),
                new Analyze.User(2, "Петров"),
                new Analyze.User(3, "Семенов"),
                new Analyze.User(4, "Федотов"),
                new Analyze.User(5, "Степаненко")
        );
        List<Analyze.User> current = List.of();
        Analyze analyze = new Analyze();
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(5));
    }

    @Test
    public void testChangeAllItemsOnList() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "Иванов"),
                new Analyze.User(2, "Петров"),
                new Analyze.User(3, "Семенов"),
                new Analyze.User(4, "Федотов"),
                new Analyze.User(5, "Степаненко")
        );
        List<Analyze.User> current = List.of(
                new Analyze.User(1, "Иванов1"),
                new Analyze.User(2, "Петров1"),
                new Analyze.User(3, "Семенов1"),
                new Analyze.User(4, "Федотов1"),
                new Analyze.User(5, "Степаненко1")
        );
        Analyze analyze = new Analyze();
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(5));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void workWithEmptyLists() {
        List<Analyze.User> previous = List.of();
        List<Analyze.User> current = List.of();
        Analyze analyze = new Analyze();
        Analyze.Info info = analyze.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }
}
