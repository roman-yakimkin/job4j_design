package ru.job4j.design.tictactoe;

/**
 * Класс - игрок
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 05.04.2020
 */
public class ConsolePlayer implements Player {
    private String name;
    private Symbol symbol;
    private DataInput dataInput;

    public ConsolePlayer(String name, Symbol symbol, DataInput dataInput, DataOutput dataOutput) {
        this.name = name;
        this.symbol = symbol;
        this.dataInput = dataInput;
        dataInput.setPlayer(this);
        dataInput.setDataOutput(dataOutput);
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
