package ru.job4j.design.tictactoe;

import java.util.List;
import java.util.Random;

/**
 * Класс - игра
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.04.2020
 * @version 1.0
 */
public class Game implements IGame {

    private IBoard board;
    private IPlayer[] players;
    private int winCount;
    private int currentPlayerIndex;

    public Game(IBoard board, IPlayer[] players, int winCount) {
        this.board = board;
        this.players = players;
        this.winCount = winCount;
        Random r = new Random();
        this.currentPlayerIndex = r.nextInt(this.players.length);
    }

    @Override
    public IBoard getBoard() {
        return this.board;
    }

    /**
     * Переход хода к следущему игроку
     */
    private void nextPlayer() {
        if (currentPlayerIndex == (players.length - 1)) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
        }
    }

    /**
     * Проверить, есть ли необходимая последовательность для выигрыша
     * @return символ, который набрал необходимую последовательность или null
     */
    @Override
    public boolean isEnoughSequence(IPlayer player) {

        // Проверка по горизонтали
        int len = 0;
        ISymbol current = null, previous = null;
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                current = board.getCell(x, y).getStatus();
                if (current == player.getSymbol()) {
                    if (previous == player.getSymbol()) {
                        len++;
                        if (len >= winCount ) {
                            return true;
                        }
                    } else {
                        len = 1;
                    }
                } else {
                    len = 0;
                }
                previous = current;
            }
        }

        // Проверка по вертикали
        len = 0;
        current = null;
        previous = null;
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                current = board.getCell(x, y).getStatus();
                if (current == player.getSymbol()) {
                    if (previous == player.getSymbol()) {
                        len++;
                        if (len >= winCount ) {
                            return true;
                        }
                    } else {
                        len = 1;
                    }
                } else {
                    len = 0;
                }
                previous = current;
            }
        }

        // Диагональ сверху вниз
        len = 0;
        current = null;
        previous = null;
        for (int x = - board.getHeight() + winCount ; x < board.getHeight() - winCount + 1 ; x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                int current_x = x - winCount + 1 + y;
                if (current_x >= 0 && current_x < board.getWidth()) {
                    current = board.getCell(current_x, y).getStatus();
                    if (current == player.getSymbol()) {
                        if (previous == player.getSymbol()) {
                            len++;
                            if (len >= winCount ) {
                                return true;
                            }
                        } else {
                            len = 1;
                        }
                    } else {
                        len = 0;
                    }
                    previous = current;
                } else {
                    len = 0;
                }
            }
        }

        // Диагональ снизу вверх
        len = 0;
        current = null;
        previous = null;
        for (int x = - board.getHeight() + winCount ; x < board.getHeight() - winCount + 1 ; x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                int current_x = x - winCount + 1 + y;
                if (current_x >= 0 && current_x < board.getWidth()) {
                    current = board.getCell(current_x, board.getHeight() - y - 1).getStatus();
                    if (current == player.getSymbol()) {
                        if (previous == player.getSymbol()) {
                            len++;
                            if (len >= winCount ) {
                                return true;
                            }
                        } else {
                            len = 1;
                        }
                    } else {
                        len = 0;
                    }
                    previous = current;
                } else {
                    len = 0;
                }
            }
        }

        return false;
    }

    @Override
    public IPlayer whoseTurn() {
        return players[currentPlayerIndex];
    }

    @Override
    public void execute() {
        IPlayer winner = null;
        System.out.println(whoseTurn().getName() + " начинает играть первым");
        do {
            board.output();
            boolean correctCoords;
            ICoords coords;
            do {
                correctCoords = true;
                coords = whoseTurn().getTurnCoords();
                if (coords.getX() < 0 || coords.getX() >= board.getWidth() || coords.getY() < 0 || coords.getY() >= board.getHeight()) {
                    System.out.println("Ваши координаты находятся за пределами доски");
                    correctCoords = false;
                }
                if (!board.getCell(coords.getX(), coords.getY()).isEmpty()) {
                    System.out.println("Данная ячейка уже занята");
                    correctCoords = false;
                }
            } while (!correctCoords);
            board.getCell(coords.getX(), coords.getY()).setStatus(whoseTurn().getSymbol());
            if (isEnoughSequence(whoseTurn())) {
                winner = whoseTurn();
            } else {
                nextPlayer();
            }
        } while (winner == null);
        System.out.println(winner.getName() + " выиграл");
        board.output();
    }

    public static void main(String[] args) {
        IBoard board = new Board(10, 10);
        IPlayer player1 = new Player("First player", ESymbol.X_SYMBOL);
        IPlayer player2 = new Player("Second player", ESymbol.O_SYMBOL);
        IGame game = new Game(board, new IPlayer[]{player1, player2}, 3);
        game.execute();
    }
}
