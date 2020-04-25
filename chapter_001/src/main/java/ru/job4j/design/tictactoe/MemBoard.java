package ru.job4j.design.tictactoe;

/**
 * Класс - игровая доска
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 05.04.2020
 */
public class MemBoard implements Board {
    private int width;
    private int height;
    private Cell[][] cells;
    private DataOutput dataOutput;

    public MemBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                cells[y][x] = new MemCell();
            }
        }
    }

    @Override
    public void setDataOutput(DataOutput dataOutput) {
        this.dataOutput = dataOutput;
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
        dataOutput.outputBoard(this);
    }

    @Override
    public Cell getCell(int x, int y) {
        return cells[y][x];
    }
}
