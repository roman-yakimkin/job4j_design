package ru.job4j.design.tictactoe;

/**
 * Интерфейс фабрики для создания классов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 14.04.2020
 * @version 1.0
 */
public interface ClassFactory {
    Board createBoard(int width, int height, DataOutput dataOutput);
    Player createPlayer(String playerName, Symbol playerSymbol, DataInput dataInput, DataOutput dataOutput);
}
