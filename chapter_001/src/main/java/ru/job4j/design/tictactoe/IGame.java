package ru.job4j.design.tictactoe;

/**
 * Интерфейс - игра
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 05.04.2020
 */
public interface IGame {
    public IBoard getBoard();
    public IPlayer whoseTurn();
    public boolean isEnoughSequence(IPlayer player);
    public void execute();
}
