package ru.job4j.design.menu;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MenuItem implements IMenuItem {
    IMenuItem parent;
    String title;
    Consumer<IMenuItem> action;

    public MenuItem(String title, Consumer<IMenuItem> action) {
        this.title = title;
        this.parent = null;
        this.action = action;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public IMenuItem getParent() {
        return parent;
    }

    @Override
    public void setParent(IMenuItem parent) {
        this.parent = parent;
    }

    @Override
    public Consumer<IMenuItem> getAction() {
        return this.action;
    }

    @Override
    public void setAction(Consumer<IMenuItem> action) {
        this.action = action;
    }

    @Override
    public void executeAction() {
        this.getAction().accept(this);
    }

    @Override
    public int getDepth() {
        int depth = 1;
        IMenuItem mi = this;
        while (mi.getParent() != null) {
            depth++;
            mi = mi.getParent();
        }
        return depth;
    }
}
