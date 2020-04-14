package ru.job4j.design.tictactoe;

/**
 * Интерфейс - ячейка игровой доски
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 05.04.2020
 */
public interface Cell {
    public Symbol getStatus();
    public Cell setStatus(Symbol symbol);
    public boolean isEmpty();
}
