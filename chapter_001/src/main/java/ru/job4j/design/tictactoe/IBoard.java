package ru.job4j.design.tictactoe;

/**
 * Интерфейс - игровая доска
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 05.04.2020
 * @version 1.0
 */
public interface IBoard {
    public int getWidth();
    public int getHeight();
    public void output();
    public ICell getCell(int x, int y);

}
