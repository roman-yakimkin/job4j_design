package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemSorterDescTest {
    @Test
    public void sortDescTest() {
        List<Item> items = Arrays.asList(
                new Item("Item 1"),
                new Item("Urgent item"),
                new Item("Important item")
        );
        items.sort(new ItemSorterDesc());
        List<String> itemNames = new ArrayList<String>();
        for (Item item : items) {
            itemNames.add(item.getName());
        }
        List<String> expected = Arrays.asList("Urgent item", "Item 1", "Important item");
        assertThat(itemNames, is(expected));
    }
}
