package ru.job4j.design.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {

    @Test
    public void testMenuItemDepth() {
        IMenu menu = new Menu(new ArrayList<>());
        IMenuItem firstItem = new MenuItem("1. First", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });

        IMenuItem secondItem = new MenuItem("1.1 Second", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem thirdItem = new MenuItem("1.1.1 Third", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });

        menu.addMenuItem(null, firstItem);
        menu.addMenuItem(firstItem, secondItem);
        menu.addMenuItem(secondItem, thirdItem);

        List<IMenuItem> foundItem = menu.filterMenuItems((mi) -> (mi.getTitle().equals("1.1.1 Third")));
        IMenuItem item = foundItem.get(0);

        assertThat(item.getDepth(), is(3));
    }

    @Test
    public void testMenuOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        IMenu menu = new Menu(new ArrayList<>());
        IMenuItem item1 = new MenuItem("Item 1", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem item11 = new MenuItem("Item 1.1", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem item111 = new MenuItem("Item 1.1.1", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem item12 = new MenuItem("Item 1.2", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem item2 = new MenuItem("Item 2", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        menu.addMenuItem(null, item1);
        menu.addMenuItem(item1, item11);
        menu.addMenuItem(item11, item111);
        menu.addMenuItem(item1, item12);
        menu.addMenuItem(null, item2);

        IMenuOutput menuOutput = new MenuOutput(menu);
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

        IMenu menu = new Menu(new ArrayList<>());
        IMenuItem item1 = new MenuItem("Item 1", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem item11 = new MenuItem("Item 1.1", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem item111 = new MenuItem("Item 1.1.1", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem item12 = new MenuItem("Item 1.2", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        IMenuItem item2 = new MenuItem("Item 2", (mi) -> {
            System.out.println(mi.getTitle() + " executed");
        });
        menu.addMenuItem(null, item1);
        menu.addMenuItem(item1, item11);
        menu.addMenuItem(item11, item111);
        menu.addMenuItem(item1, item12);
        menu.addMenuItem(null, item2);

        List<IMenuItem> foundItem = menu.filterMenuItems((mi) -> (mi.getTitle().equals("Item 1.1.1")));
        IMenuItem item = foundItem.get(0);

        item.executeAction();

        String expect = "Item 1.1.1 executed" + System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }
}
