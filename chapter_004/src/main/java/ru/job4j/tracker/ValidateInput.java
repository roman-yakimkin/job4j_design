package ru.job4j.tracker;

import org.springframework.stereotype.Component;

/**
 * Циклический консольный ввод с проверкой значений
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 06.02.2020
 * @version 1.0
 */
@Component
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Получить строковое значение
     * @param question - текст приглашение ко вводу
     * @return - строковое значение
     */
    @Override
    public String askStr(String question) {
        return input.askStr(question);
    }

    /**
     * Циклический ввод целого числа
     * @param question - текст приглашения ко вводу
     * @return - целое число
     */
    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please input valid data again.");
            }
        } while (invalid);
        return value;
    }

    /**
     * Циклический ввод целого числа в заданном промежутке
     * @param question - текст приглашения ко вводу
     * @param max - максимальное значение
     * @return - целое число
     */
    @Override
    public int askInt(String question, int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                System.out.println("Please select a key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please input valid data again.");
            }
        } while (invalid);
        return value;
    }
}
