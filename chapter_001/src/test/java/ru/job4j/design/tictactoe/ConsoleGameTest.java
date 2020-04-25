package ru.job4j.design.tictactoe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleGameTest {

    @Test
    public void isPlayerXWonByHorizontal() {
        Game game = new ConsoleGame(new ConsoleClassFactory(), 10, 10,
                "First Player", ESymbol.X_SYMBOL, new ConsoleDataInput(),
                "Second Player", ESymbol.O_SYMBOL, new ConsoleDataInput(),
                new ConsoleDataOutput(), 3, -1);
        game.getBoard().getCell(0, 0).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(1, 0).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(2, 0).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(game.getPlayer(ESymbol.X_SYMBOL)), is(true));
    }

    @Test
    public void isPlayerXWonByVertical() {
        Game game = new ConsoleGame(new ConsoleClassFactory(), 10, 10,
                "First Player", ESymbol.X_SYMBOL, new ConsoleDataInput(),
                "Second Player", ESymbol.O_SYMBOL, new ConsoleDataInput(),
                new ConsoleDataOutput(), 3, -1);
        game.getBoard().getCell(3, 3).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(3, 4).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(3, 5).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(game.getPlayer(ESymbol.X_SYMBOL)), is(true));
    }

    @Test
    public void isPlayerXWonByDiagonal10() {
        Game game = new ConsoleGame(new ConsoleClassFactory(), 10, 10,
                "First Player", ESymbol.X_SYMBOL, new ConsoleDataInput(),
                "Second Player", ESymbol.O_SYMBOL, new ConsoleDataInput(),
                new ConsoleDataOutput(), 3, -1);
        game.getBoard().getCell(0, 0).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(1, 1).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(2, 2).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(game.getPlayer(ESymbol.X_SYMBOL)), is(true));
    }

    @Test
    public void isPlayerXWonByDiagonal11() {
        Game game = new ConsoleGame(new ConsoleClassFactory(), 10, 10,
                "First Player", ESymbol.X_SYMBOL, new ConsoleDataInput(),
                "Second Player", ESymbol.O_SYMBOL, new ConsoleDataInput(),
                new ConsoleDataOutput(), 3, -1);
        game.getBoard().getCell(0, 7).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(1, 8).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(2, 9).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(game.getPlayer(ESymbol.X_SYMBOL)), is(true));
    }

    @Test
    public void isPlayerXWonByDiagonal20() {
        Game game = new ConsoleGame(new ConsoleClassFactory(), 10, 10,
                "First Player", ESymbol.X_SYMBOL, new ConsoleDataInput(),
                "Second Player", ESymbol.O_SYMBOL, new ConsoleDataInput(),
                new ConsoleDataOutput(), 3, -1);
        game.getBoard().getCell(2, 0).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(1, 1).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(0, 2).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(game.getPlayer(ESymbol.X_SYMBOL)), is(true));
    }
}