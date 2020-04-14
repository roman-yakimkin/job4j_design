package ru.job4j.design.tictactoe;

/**
 * Класс игрока - бота, который берет ходы из внешнего массива
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 14.04.2020
 * @version 1.0
 */
public class DummyPlayer implements Player {
    private String name;
    private Symbol symbol;
    private DataInput dataInput;

    public DummyPlayer(String name, Symbol symbol, DataInput dataInput) {
        this.name = name;
        this.symbol = symbol;
        this.dataInput = dataInput;
        dataInput.setPlayer(this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;
    }

    @Override
    public Coords getTurnCoords() {
        return dataInput.getCoords();
    }
}
