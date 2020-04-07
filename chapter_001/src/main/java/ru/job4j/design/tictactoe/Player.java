package ru.job4j.design.tictactoe;

import java.util.Scanner;

/**
 * Класс - игрок
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 05.04.2020
 */
public class Player implements IPlayer {
    private String name;
    private ISymbol symbol;

    public Player(String name, ISymbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ISymbol getSymbol() {
        return this.symbol;
    }

    @Override
    public ICoords getTurnCoords() {
        Scanner in = new Scanner(System.in);
        boolean correct;
        int x = 0, y = 0;
        do {
            correct = true;
            System.out.print(this.getName() + " сейчас ходит. Введите координаты X и Y, разделенные пробелом, куда будет помещен " + getSymbol().toString() + " :");
            String strCoords = in.nextLine();
            String[] coords = strCoords.trim().split("\\s");
            try {
                x = Integer.parseInt(coords[0]);
                y = Integer.parseInt(coords[1]);
            } catch (Exception e) {
                System.out.println("Вы ввели некорректные значения. Пожалуйста, введите их заново");
                correct = false;
            }
        } while (!correct);
//        in.close();
        return new Coords(x, y);
    }
}
