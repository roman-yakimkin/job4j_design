package ru.job4j.design.menu;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Интерфейс для одной команды меню
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.04.2020
 * @version 1.0
 */
public interface IMenuItem extends ITreeElement, IActionListener {
    public String getTitle();
    public void setTitle(String title);
}
