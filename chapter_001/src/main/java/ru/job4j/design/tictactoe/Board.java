package ru.job4j.design.tictactoe;

/**
 * Класс - игровая доска
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 05.04.2020
 */
public class Board implements IBoard {
    private int width;
    private int height;
    private ICell[][] cells;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new ICell[width][height];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++ ) {
                cells[y][x] = new Cell();
            }
        }
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void output() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x].getStatus().display();
            }
            System.out.print(System.lineSeparator());
        }
    }

    @Override
    public ICell getCell(int x, int y) {
        return cells[y][x];
    }
}
