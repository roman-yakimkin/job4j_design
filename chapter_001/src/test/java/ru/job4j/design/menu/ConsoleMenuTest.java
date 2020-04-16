package ru.job4j.design.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleMenuTest {

    @Test
    public void testMenuItemDepth() {
        Menu menu = new ConsoleMenu(new ArrayList<>());
        MenuItem firstItem = new ConsoleMenuItem("1. First");
        firstItem.on("click", (mi) -> {
            System.out.println(mi.getTitle() + " clicked");
        });

        MenuItem secondItem = new ConsoleMenuItem("1.1 Second");
        secondItem.on("click", (mi) -> {
            System.out.println(mi.getTitle() + " clicked");
        });

        MenuItem thirdItem = new ConsoleMenuItem("1.1.1 Third");
        thirdItem.on("click", (mi) -> {
            System.out.println(mi.getTitle() + " clicked");
        });

        menu.add(null, firstItem);
        menu.add(firstItem, secondItem);
        menu.add(secondItem, thirdItem);

        List<MenuItem> foundItem = menu.filterMenuItems((mi) -> (mi.getTitle().equals("1.1.1 Third")));
        MenuItem item = foundItem.get(0);

        assertThat(item.getDepth(), is(3));
    }

    @Test
    public void testMenuOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        Menu menu = new ConsoleMenu(new ArrayList<>());
        MenuItem item1 = new ConsoleMenuItem("Item 1");
        item1.on("hover", (mi) -> {
            System.out.println(mi.getTitle() + " hovered");
        });

        MenuItem item11 = new ConsoleMenuItem("Item 1.1");
        item11.on("hover", (mi) -> {
            System.out.println(mi.getTitle() + " hovered");
        });

        MenuItem item111 = new ConsoleMenuItem("Item 1.1.1");
        item111.on("hover", (mi) -> {
            System.out.println(mi.getTitle() + " hovered");
        });

        MenuItem item12 = new ConsoleMenuItem("Item 1.2");
        item12.on("hover", (mi) -> {
            System.out.println(mi.getTitle() + " hovered");
        });

        MenuItem item2 = new ConsoleMenuItem("Item 2");
        item2.on("hover", (mi) -> {
            System.out.println(mi.getTitle() + " hovered");
        });

        menu.add(null, item1);
        menu.add(item1, item11);
        menu.add(item11, item111);
        menu.add(item1, item12);
        menu.add(null, item2);

        MenuOutput menuOutput = new ConsoleMenuOutput(menu, System.out);
        menuOutput.output();

        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Item 1")
                .add("--Item 1.1")
                .add("----Item 1.1.1")
                .add("--Item 1.2")
                .add("Item 2")
                .toString();

        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }

    @Test
    public void testMenuItemExecute() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        Menu menu = new ConsoleMenu(new ArrayList<>());
        MenuItem item1 = new ConsoleMenuItem("Item 1");
        item1.on("someevent", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        MenuItem item11 = new ConsoleMenuItem("Item 1.1");
        item11.on("someevent", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        MenuItem item111 = new ConsoleMenuItem("Item 1.1.1");
        item111.on("someevent", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        item111.on("click", (mi) -> {
            System.out.println(mi.getTitle() + " clicked");
        });
        MenuItem item12 = new ConsoleMenuItem("Item 1.2");
        item12.on("someevent", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        MenuItem item2 = new ConsoleMenuItem("Item 2");
        item2.on("someevent", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });

        menu.add(null, item1);
        menu.add(item1, item11);
        menu.add(item11, item111);
        menu.add(item1, item12);
        menu.add(null, item2);

        List<MenuItem> foundItem = menu.filterMenuItems((mi) -> (mi.getTitle().equals("Item 1.1.1")));
        MenuItem item = foundItem.get(0);

        item.trigger("someevent");
        item.trigger("click");

        String expect = "Item 1.1.1 executed" + System.lineSeparator() + "Item 1.1.1 clicked" + System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }
}
