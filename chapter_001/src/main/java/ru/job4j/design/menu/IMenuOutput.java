package ru.job4j.design.menu;

/**
 * Интерфейс для отображения меню
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.04.2020
 * @version 1.0
 */
public interface IMenuOutput {
    public IMenu getMenu();
    public void output();
}
