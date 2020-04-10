package ru.job4j.design.menu;

/**
 * Интерфейс для работы с событиями
 */
public interface ActionListener {
    public void on(String actionName, Action action);
    public void off(String actionName);
    public boolean trigger(String actionName);
}
