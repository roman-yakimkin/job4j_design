package ru.job4j.design.tictactoe;

/**
 * Класс для вывода данных для консоли
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 14.04.2020
 * @version 1.0
 */
public class ConsoleDataOutput implements DataOutput {
    /**
     * Отобразить игровую доску
     * @param board - игровая доска
     */
    @Override
    public void outputBoard(Board board) {
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                outputCell(board.getCell(x, y));
            }
            System.out.print(System.lineSeparator());
        }
    }

    /**
     * Отобразить одну ячейку игровой доски
     * @param cell - ячейка
     */
    @Override
    public void outputCell(Cell cell) {
        cell.getStatus().display();
    }

    @Override
    public void outputMessage(String message) {
        System.out.println(message);
    }
}
