package ru.job4j.design.tictactoe;

/**
 * Интерфейс для вывода данных
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 14.04.2020
 * @version 1.0
 */
public interface DataOutput {
    public void outputBoard(Board board);
    public void outputCell(Cell cell);
    public void outputMessage(String message);
}
