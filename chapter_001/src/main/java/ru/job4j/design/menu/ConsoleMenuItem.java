package ru.job4j.design.menu;

import java.util.HashMap;
import java.util.Map;

public class ConsoleMenuItem implements MenuItem {
    private TreeElement parent = null;
    private String title;
    private Map<String, Action> actions;

    public ConsoleMenuItem(String title) {
        this.title = title;
        actions = new HashMap<String, Action>();
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
    public TreeElement getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeElement parent) {
        this.parent = parent;
    }

    @Override
    public void on(String actionName, Action action) {
        actions.put(actionName, action);
    }

    @Override
    public void off(String actionName) {
        actions.remove(actionName);
    }

    @Override
    public boolean trigger(String actionName) {
        Action action = actions.get(actionName);
        if (action != null) {
            action.execute(this);
        }
        return (action != null);
    }

    @Override
    public int getDepth() {
        int depth = 1;
        TreeElement mi = this;
        while (mi.getParent() != null) {
            depth++;
            mi = mi.getParent();
        }
        return depth;
    }
}
