package ru.job4j.design.tictactoe;

/**
 * Интерфейс - игра
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 05.04.2020
 */
public interface Game {
    public Board getBoard();
    public Player getPlayer(Symbol symbol);
    public Player whoseTurn();
    public boolean isEnoughSequence(Player player);
    public void execute();
}
