package ru.job4j.design.menu;

/**
 * Класс для отображения меню
 */
public class MenuOutput implements IMenuOutput {
    IMenu menu;

    public MenuOutput(IMenu menu) {
        this.menu = menu;
    }

    @Override
    public IMenu getMenu() {
        return this.menu;
    }

    private String getOutputMenuItem(IMenuItem mi) {
        String result = mi.getTitle();
        for (int i = 1; i < mi.getDepth(); i++) {
            result = "--" + result;
        }
        return result;
    }

    private void recursiveMenuItemOutput(IMenuItem mi) {
        System.out.println(getOutputMenuItem(mi));
        for (IMenuItem child : menu.filterMenuItems((arg) -> (arg.getParent() == mi))) {
            recursiveMenuItemOutput(child);
        }
    }

    @Override
    public void output() {
        for (IMenuItem mi : this.getMenu().filterMenuItems((i) -> (i.getParent() == null))) {
            recursiveMenuItemOutput(mi);

        }
    }
}
