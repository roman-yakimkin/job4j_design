package ru.job4j.tracker;

import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Консольный ввод информации
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 31.01.2020
 * @version 1.0
 */
@Component
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Ввод строкового значения в консоли
     * @param question - текст приглашения ко вводу
     * @return - введенная строка
     */
    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Ввод целочисленного значения в консоли
     * @param question - текст приглашения ко вводу
     * @return - целое число
     */
    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    /**
     * Ввод целочисленного значения от 0 до max с приглашением ко вводу
     * @param question - текст приглашения ко вводу
     * @param max - максимальное значение
     * @return - целое число
     */
    @Override
    public int askInt(String question, int max) throws IllegalStateException {
        int select = askInt(question);
        if (select < 0 || select > max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}
