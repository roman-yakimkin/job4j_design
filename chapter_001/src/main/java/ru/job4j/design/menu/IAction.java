package ru.job4j.design.menu;

/**
 * Действие при выворе конкретного пункта меню
 */

@FunctionalInterface
public interface IAction {
    public void execute(IMenuItem mi);
}
