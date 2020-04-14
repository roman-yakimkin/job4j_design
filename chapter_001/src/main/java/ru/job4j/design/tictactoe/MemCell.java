package ru.job4j.design.tictactoe;

/**
 * Класс - ячейка на игровой доске
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 07.04.2020
 */
public class MemCell implements Cell {
    private Symbol status;

    public MemCell() {
        this.status = ESymbol.EMPTY_SYMBOL;
    }

    @Override
    public Symbol getStatus() {
        return status;
    }

    @Override
    public Cell setStatus(Symbol symbol) {
        this.status = symbol;
        return this;
    }

    @Override
    public boolean isEmpty() {
        return (status == ESymbol.EMPTY_SYMBOL);
    }
}
