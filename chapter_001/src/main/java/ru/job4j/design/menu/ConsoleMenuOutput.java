package ru.job4j.design.menu;

import java.io.PrintStream;

/**
 * Класс для отображения меню
 */
public class ConsoleMenuOutput implements MenuOutput {
    Menu menu;
    PrintStream printStream;

    public ConsoleMenuOutput(Menu menu, PrintStream printStream) {
        this.menu = menu;
        this.printStream = printStream;
    }

    @Override
    public Menu getMenu() {
        return menu;
    }

    private String getOutputMenuItem(MenuItem mi) {
        String result = mi.getTitle();
        for (int i = 1; i < mi.getDepth(); i++) {
            result = "--" + result;
        }
        return result;
    }

    private void recursiveMenuItemOutput(MenuItem mi) {
        printStream.println(getOutputMenuItem(mi));
        for (MenuItem child : menu.filterMenuItems((arg) -> (arg.getParent() == mi))) {
            recursiveMenuItemOutput(child);
        }
    }

    @Override
    public void output() {
        for (MenuItem mi : menu.filterMenuItems((i) -> (i.getParent() == null))) {
            recursiveMenuItemOutput(mi);
        }
    }
}
