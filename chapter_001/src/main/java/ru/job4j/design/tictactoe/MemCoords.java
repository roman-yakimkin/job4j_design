package ru.job4j.design.tictactoe;

/**
 * Класс - двумерные координаты
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 05.04.2020
 * @version 1.0
 */
public class MemCoords implements Coords {
    private int x;
    private int y;

    public MemCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MemCoords(Coords coords) {
        this.x = coords.getX();
        this.y = coords.getY();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public Coords setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Coords setY(int y) {
        this.y = y;
        return this;
    }
}
