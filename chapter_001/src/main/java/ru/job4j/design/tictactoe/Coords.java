package ru.job4j.design.tictactoe;

/**
 * Класс - двумерные координаты
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 05.04.2020
 * @version 1.0
 */
public class Coords implements ICoords {
    private int x;
    private int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public ICoords setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ICoords setY(int y) {
        this.y = y;
        return this;
    }
}
