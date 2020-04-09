package ru.job4j.design.menu;

import java.util.HashMap;
import java.util.Map;

public class MenuItem implements IMenuItem {
    ITreeElement parent = null;
    String title;
    Map<String, IAction> actions;

    public MenuItem(String title) {
        this.title = title;
        actions = new HashMap<String, IAction>();
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
    public ITreeElement getParent() {
        return parent;
    }

    @Override
    public void setParent(ITreeElement parent) {
        this.parent = parent;
    }

    @Override
    public void on(String actionName, IAction action) {
        actions.put(actionName, action);
    }

    @Override
    public void off(String actionName) {
        actions.remove(actionName);
    }

    @Override
    public boolean trigger(String actionName) {
        IAction action = actions.get(actionName);
        if (action != null) {
            action.execute(this);
        }
        return (action != null);
    }

    @Override
    public int getDepth() {
        int depth = 1;
        ITreeElement mi = this;
        while (mi.getParent() != null) {
            depth++;
            mi = mi.getParent();
        }
        return depth;
    }
}
