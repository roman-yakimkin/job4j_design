package ru.job4j.design.menu;

/**
 * Действие при выворе конкретного пункта меню
 */
public interface Action {
    public void execute(MenuItem mi);
}
