package ru.job4j.design.tictactoe;

/**
 * Символ на игровой доске
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 05.04.2020
 * @version 1.0
 */
public interface ISymbol {
    public void display();
    ISymbol get();
}
