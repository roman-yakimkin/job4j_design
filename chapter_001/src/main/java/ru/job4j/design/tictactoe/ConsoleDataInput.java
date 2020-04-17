package ru.job4j.design.tictactoe;

import java.util.Scanner;

/**
 * Класс - получатель координат из консоли
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 13.04.2020
 * @version 1.0
 */
public class ConsoleDataInput implements DataInput {
    private Player player;
    private DataOutput dataOutput;

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void setDataOutput(DataOutput dataOutput) {
        this.dataOutput = dataOutput;
    }

    /**
     * Получить координаты
     * @return - координаты
     */
    @Override
    public Coords getCoords() {
        Scanner in = new Scanner(System.in);
        boolean correct;
        int x = 0, y = 0;
        do {
            correct = true;
            dataOutput.outputMessage(player.getName() + " сейчас ходит. Введите координаты X и Y, разделенные пробелом, куда будет помещен " + player.getSymbol().toString() + " :");
            String strCoords = in.nextLine();
            String[] coords = strCoords.trim().split("\\s");
            try {
                x = Integer.parseInt(coords[0]);
                y = Integer.parseInt(coords[1]);
            } catch (Exception e) {
                dataOutput.outputMessage("Вы ввели некорректные значения. Пожалуйста, введите их заново");
                correct = false;
            }
        } while (!correct);
        return new MemCoords(x, y);
    }
}
