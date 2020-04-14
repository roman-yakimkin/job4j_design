package ru.job4j.design.tictactoe;

/**
 * Интерфейс - поставщик координат для бота
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 13.04.2020
 * @version 1.0
 */
public interface DataInput {
    public void setPlayer(Player player);
    public Coords getCoords();
    public void setDataOutput(DataOutput dataOutput);
}
