package ru.job4j.design.menu;

/**
 * Интерфейс для одной команды меню
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.04.2020
 * @version 1.0
 */
public interface MenuItem extends TreeElement, ActionListener {
    public String getTitle();
    public void setTitle(String title);
}
