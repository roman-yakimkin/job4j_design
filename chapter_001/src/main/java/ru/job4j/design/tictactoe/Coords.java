package ru.job4j.design.tictactoe;

/**
 * Интерфейс для координат
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 14.04.2020
 * @version 1.0
 */
public interface Coords {
    public int getX();
    public int getY();
    public Coords setX(int x);
    public Coords setY(int y);
}
