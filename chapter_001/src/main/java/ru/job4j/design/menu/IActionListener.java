package ru.job4j.design.menu;

/**
 * Интерфейс для работы с событиями
 */
public interface IActionListener {
    public void on(String actionName, IAction action);
    public void off(String actionName);
    public boolean trigger(String actionName);
}
