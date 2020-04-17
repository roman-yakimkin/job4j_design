package ru.job4j.design.tictactoe;

/**
 * Фабрика для создания классов для консоли
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 14.04.2020
 * @version 1.0
 */
public class ConsoleClassFactory implements ClassFactory {
    /**
     * Создать ировую доску
     * @param width - ширина
     * @param height - высота
     * @param dataOutput - ссылка на интерфейс вывода данных
     * @return - экземпляр доски
     */
    @Override
    public Board createBoard(int width, int height, DataOutput dataOutput) {
        Board result = new MemBoard(width, height);
        result.setDataOutput(dataOutput);
        return result;
    }

    /**
     * Создать игрока
     * @param playerName - имя игрока
     * @param playerSymbol - символ игрока
     * @param dataInput - интерфейс ввода данных
     * @param dataOutput - интерфейс вывода данных
     * @return - экземпляр игрока
     */
    @Override
    public Player createPlayer(String playerName, Symbol playerSymbol, DataInput dataInput, DataOutput dataOutput) {
        Player result = new ConsolePlayer(playerName, playerSymbol, dataInput, dataOutput);
        return result;
    }
}
