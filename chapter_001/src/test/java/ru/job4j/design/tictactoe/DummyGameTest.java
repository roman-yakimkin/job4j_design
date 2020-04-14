package ru.job4j.design.tictactoe;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DummyGameTest {

    @Test
    public void firstPlayerShouldWin() {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        DataInput dataInput1 = new BotDataInput("1 1, 2 2, 3 3, 5 5");
        DataInput dataInput2 = new BotDataInput("2 3, 4 6, 4 7, 6 8");
        Game game = new ConsoleGame( new ConsoleClassFactory(), 10, 10
                , "First Player", ESymbol.X_SYMBOL, dataInput1
                , "Second Player", ESymbol.O_SYMBOL, dataInput2
                , new ConsoleDataOutput()
                , 3, 0);
        game.execute();
        String st = outputStream.toString();
        boolean result = outputStream.toString().contains("First Player выиграл");
        assertThat(result, is(true));
        System.setOut(consoleStream);
    }

    @Test
    public void secondPlayerShouldWin() {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        DataInput dataInput1 = new BotDataInput("1 1, 2 2, 3 1, 5 5");
        DataInput dataInput2 = new BotDataInput("2 3, 3 4, 4 5, 6 8");
        Game game = new ConsoleGame( new ConsoleClassFactory(), 10, 10
                , "First Player", ESymbol.X_SYMBOL, dataInput1
                , "Second Player", ESymbol.O_SYMBOL, dataInput2
                , new ConsoleDataOutput()
                , 3, 0);
        game.execute();
        boolean result = outputStream.toString().contains("Second Player выиграл");
        assertThat(result, is(true));
        System.setOut(consoleStream);
    }

}
