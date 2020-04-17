package ru.job4j.design.tictactoe;

import java.util.Random;

/**
 * Класс - игра
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.04.2020
 * @version 1.0
 */
public class ConsoleGame implements Game {

    private Board board;
    private Player[] players;
    private int winCount;
    private int currentPlayerIndex;
    private DataOutput dataOutput;

    /**
     * Конструктор игры
     * @param classFactory - фабрика для создания классов
     * @param boardWidth - ширина доски
     * @param boardHeight - высота доски
     * @param firstPlayerName - имя первого игрока
     * @param firstPlayerSymbol - символ первого игрока
     * @param firstPlayerDataInput - интерфейс ввода данных первого игрока
     * @param secondPlayerName - имя второго игрока
     * @param secondPlayerSymbol - символ второго игрока
     * @param secondPlayerDataInput - интерфейс ввода данных второго игрока
     * @param dataOutput - интерфейс вывода данных
     * @param winCount - количество символов для выигрыша
     * @param firstPlayerIndex - кто будет начинать игру (если -1, то будет разыгрываться случайно)
     */
    public ConsoleGame(ClassFactory classFactory
            ,int boardWidth, int boardHeight
            , String firstPlayerName, Symbol firstPlayerSymbol, DataInput firstPlayerDataInput
            , String secondPlayerName, Symbol secondPlayerSymbol, DataInput secondPlayerDataInput
            , DataOutput dataOutput
            , int winCount, int firstPlayerIndex) {
        this.board = classFactory.createBoard(boardWidth, boardHeight, dataOutput);
        Player player1 = classFactory.createPlayer(firstPlayerName, firstPlayerSymbol, firstPlayerDataInput, dataOutput);
        Player player2 = classFactory.createPlayer(secondPlayerName, secondPlayerSymbol, secondPlayerDataInput, dataOutput);
        this.players = new Player[]{player1, player2};
        this.winCount = winCount;
        this.dataOutput = dataOutput;
        Random r = new Random();
        if (firstPlayerIndex == -1) {
            this.currentPlayerIndex = r.nextInt(this.players.length);
        } else {
            this.currentPlayerIndex = firstPlayerIndex;
        }
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    /**
     * Получить игрока по его символу
     * @param symbol - символ (крестик или нолик)
     * @return - игрок
     */
    @Override
    public Player getPlayer(Symbol symbol) {
        Player result = null;
        for(Player pl : players) {
            if (pl.getSymbol().equals(symbol)) {
                result = pl;
            }
        }
        return result;
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

    private boolean checkSequenceHorizontal(Player player) {
        int len = 0;
        Symbol current = null, previous = null;
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
        return false;
    }

    /**
     * Проверка последовательности символов для игрока по вертикали
     * @param player - игрок
     * @return - истина, если данный игрок имеет последовательность по вертикали
     */
    private boolean checkSequenceVertical(Player player) {
        int len = 0;
        Symbol current = null, previous = null;
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
        return false;
    }

    /**
     * Проверка последовательности символов для игрока по диагонали сверху вниз
     * @param player - игрок
     * @return - истина, если данный игрок имеет достаточную последовательность
     */
    private boolean checkSequenceDiagonalFromTopToBottom(Player player) {
        int len = 0;
        Symbol current = null, previous = null;
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
        return false;
    }

    /**
     * Проверка последовательности символов для игрока по диагонали снизу вверх
     * @param player - игрок
     * @return - истина, если данный игрок имеет достаточную последовательность
     */
    private boolean checkSequenceDiagonalFromBottomToTop(Player player) {
        int len = 0;
        Symbol current = null, previous = null;
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

    /**
     * Проверить, есть ли необходимая последовательность для выигрыша
     * @return символ, который набрал необходимую последовательность или null
     */
    @Override
    public boolean isEnoughSequence(Player player) {
        return checkSequenceHorizontal(player)
            || checkSequenceVertical(player)
            || checkSequenceDiagonalFromTopToBottom(player)
            || checkSequenceDiagonalFromBottomToTop(player);
    }

    /**
     * Который игрок сейчас ходит
     * @return игрок, который сейчас ходит
     */
    @Override
    public Player whoseTurn() {
        return players[currentPlayerIndex];
    }

    /**
     * Запустить игру
     */
    @Override
    public void execute() {
        Player winner = null;
        dataOutput.outputMessage(whoseTurn().getName() + " начинает играть первым");
        do {
            board.output();
            boolean correctCoords;
            Coords coords;
            do {
                correctCoords = true;
                coords = whoseTurn().getTurnCoords();
                if (coords.getX() < 0 || coords.getX() >= board.getWidth() || coords.getY() < 0 || coords.getY() >= board.getHeight()) {
                    dataOutput.outputMessage("Ваши координаты находятся за пределами доски");
                    correctCoords = false;
                }
                if (!board.getCell(coords.getX(), coords.getY()).isEmpty()) {
                    dataOutput.outputMessage("Данная ячейка уже занята");
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
        dataOutput.outputMessage(winner.getName() + " выиграл");
        board.output();
    }

    public static void main(String[] args) {
        Game game = new ConsoleGame(
                  new ConsoleClassFactory()
                , 10, 10
                , "First Player", ESymbol.X_SYMBOL, new ConsoleDataInput()
                , "Second Player", ESymbol.O_SYMBOL, new ConsoleDataInput()
                , new ConsoleDataOutput()
                , 3, -1);

        game.execute();
    }
}
