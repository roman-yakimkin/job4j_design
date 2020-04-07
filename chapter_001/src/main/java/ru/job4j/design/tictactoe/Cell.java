package ru.job4j.design.tictactoe;

/**
 * Класс - ячейка на игровой доске
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 07.04.2020
 */
public class Cell implements ICell {
    private ISymbol status;

    public Cell() {
        this.status = ESymbol.EMPTY_SYMBOL;
    }

    @Override
    public ISymbol getStatus() {
        return status;
    }

    @Override
    public ICell setStatus(ISymbol symbol) {
        this.status = symbol;
        return this;
    }

    @Override
    public boolean isEmpty() {
        return (status == ESymbol.EMPTY_SYMBOL);
    }
}
