package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ConsoleInput.class);
        context.register(ValidateInput.class);

        context.register(CreateAction.class);
        context.register(ShowAllAction.class);
        context.register(ReplaceAction.class);
        context.register(DeleteAction.class);
        context.register(FindByIdAction.class);
        context.register(FindByNameAction.class);
        context.register(ExitAction.class);

        context.register(StartUI.class);
        Logger logger = LogManager.getLogger(io.UsageLog4j.class.getName());
        context.register(SqlTracker.class);
        context.refresh();

        Consumer<String> output = System.out::println;
        try (Store tracker = context.getBean(SqlTracker.class)) {
            tracker.init();
            List<UserAction> actions = new ArrayList<UserAction>(Arrays.asList(
                    context.getBean(CreateAction.class),
                    context.getBean(ShowAllAction.class),
                    context.getBean(ReplaceAction.class),
                    context.getBean(DeleteAction.class),
                    context.getBean(FindByIdAction.class),
                    context.getBean(FindByNameAction.class),
                    context.getBean(ExitAction.class)
            ));
            context.getBean(StartUI.class).init(context.getBean(ValidateInput.class), tracker, actions, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
