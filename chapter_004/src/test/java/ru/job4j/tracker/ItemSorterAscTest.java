package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemSorterAscTest {
    @Test
    public void sortAscTest() {
        List<Item> items = Arrays.asList(
                new Item("Item 1"),
                new Item("Urgent item"),
                new Item("Important item")
        );
        items.sort(new ItemSorterAsc());
        List<String> itemNames = new ArrayList<String>();
        for (Item item : items) {
            itemNames.add(item.getName());
        }
        List<String> expected = Arrays.asList("Important item", "Item 1", "Urgent item");
        assertThat(itemNames, is(expected));
    }

}
