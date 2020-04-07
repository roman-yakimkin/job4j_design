package ru.job4j.design.tictactoe;

/**
 * Интерфейс - игрок
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 05.04.2020
 * @version 1.0
 */
public interface IPlayer {
    public String getName();
    public ISymbol getSymbol();
    public ICoords getTurnCoords();
}
