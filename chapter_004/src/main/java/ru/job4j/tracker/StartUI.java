package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * Реализация консольного интерфейса для трекера заявок
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.01.2020
 * @version 2.0
 */
@Component
public class StartUI {
    /**
     * Инициализация и цикл работы консольного интерфейса
     * @param input - реализация интерфейса "сканер"
     * @param tracker - класс - трекер
     */
    public void init(Input input, Store tracker, List<UserAction> actions, Consumer<String> output) {

        boolean run = true;
        do {
            showMenu(actions, output);
            int menuItem = input.askInt("Select a number from 0 to 6  ", actions.size());
            UserAction action = actions.get(menuItem);
            run = action.execute(input, tracker, output);
        } while (run);
    }

    /**
     * Отобразить консольное меню
     */
    private void showMenu(List<UserAction> actions, Consumer<String> output) {
        output.accept("Menu (ver. 3)");
        for (UserAction action : actions) {
            output.accept(actions.indexOf(action) + ". " + action.name());
        }
    }
}