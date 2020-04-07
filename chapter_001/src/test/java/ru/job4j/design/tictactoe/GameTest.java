package ru.job4j.design.tictactoe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Test
    public void isPlayerXWonByHorizontal() {
        IBoard board = new Board(10, 10);
        IPlayer playerX = new Player("X player", ESymbol.X_SYMBOL);
        IPlayer playerO = new Player("O player", ESymbol.O_SYMBOL);
        IGame game = new Game(board, new IPlayer[] {playerX, playerO}, 3);
        game.getBoard().getCell(0 ,0).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(1 ,0).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(2 ,0).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(playerX), is(true));
    }

    @Test
    public void isPlayerXWonByVertical() {
        IBoard board = new Board(10, 10);
        IPlayer playerX = new Player("X player", ESymbol.X_SYMBOL);
        IPlayer playerO = new Player("O player", ESymbol.O_SYMBOL);
        IGame game = new Game(board, new IPlayer[] {playerX, playerO}, 3);
        game.getBoard().getCell(3 ,3).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(3 ,4).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(3 ,5).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(playerX), is(true));
    }

    @Test
    public void isPlayerXWonByDiagonal10() {
        IBoard board = new Board(10, 10);
        IPlayer playerX = new Player("X player", ESymbol.X_SYMBOL);
        IPlayer playerO = new Player("O player", ESymbol.O_SYMBOL);
        IGame game = new Game(board, new IPlayer[] {playerX, playerO}, 3);
        game.getBoard().getCell(0 ,0).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(1 ,1).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(2 ,2).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(playerX), is(true));
    }

    @Test
    public void isPlayerXWonByDiagonal11() {
        IBoard board = new Board(10, 10);
        IPlayer playerX = new Player("X player", ESymbol.X_SYMBOL);
        IPlayer playerO = new Player("O player", ESymbol.O_SYMBOL);
        IGame game = new Game(board, new IPlayer[] {playerX, playerO}, 3);
        game.getBoard().getCell(0 ,7).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(1 ,8).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(2 ,9).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(playerX), is(true));
    }

    @Test
    public void isPlayerXWonByDiagonal20() {
        IBoard board = new Board(10, 10);
        IPlayer playerX = new Player("X player", ESymbol.X_SYMBOL);
        IPlayer playerO = new Player("O player", ESymbol.O_SYMBOL);
        IGame game = new Game(board, new IPlayer[] {playerX, playerO}, 3);
        game.getBoard().getCell(2 ,0).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(1 ,1).setStatus(ESymbol.X_SYMBOL);
        game.getBoard().getCell(0 ,2).setStatus(ESymbol.X_SYMBOL);
        assertThat(game.isEnoughSequence(playerX), is(true));
    }

}
