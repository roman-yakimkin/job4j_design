package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * The main class
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 14.07.2020
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.reg(ConsoleInput.class);
        Input validate = new ValidateInput(context.get(ConsoleInput.class));
//        context.reg(ValidateInput.class);

        context.reg(CreateAction.class);
        context.reg(ShowAllAction.class);
        context.reg(ReplaceAction.class);
        context.reg(DeleteAction.class);
        context.reg(FindByIdAction.class);
        context.reg(FindByNameAction.class);
        context.reg(ExitAction.class);

        context.reg(StartUI.class);

        Consumer<String> output = System.out::println;
        Logger logger = LogManager.getLogger(io.UsageLog4j.class.getName());
        try (Store tracker = new SqlTracker(logger)) {
            tracker.init();
            List<UserAction> actions = new ArrayList<UserAction>(Arrays.asList(
                    context.get(CreateAction.class),
                    context.get(ShowAllAction.class),
                    context.get(ReplaceAction.class),
                    context.get(DeleteAction.class),
                    context.get(FindByIdAction.class),
                    context.get(FindByNameAction.class),
                    context.get(ExitAction.class)
            ));
            context.get(StartUI.class).init(validate, tracker, actions, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
