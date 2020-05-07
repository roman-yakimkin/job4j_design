package ru.job4j.chat;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Состояние чата - собеседник
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 28.04.2020
 * @version 1.0
 */
public class InterlocutorState implements ChatState {
    private String answersFileName;
    private Logger logger;

    public InterlocutorState(String answersFileName, Logger logger) {
        this.answersFileName = answersFileName;
        this.logger = logger;
    }

    @Override
    public void doAction() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(answersFileName)))) {
            List<String> answers = reader.lines().collect(Collectors.toList());
            Random r = new Random();
            int lineIndex = r.nextInt(answers.size());
            String line = answers.get(lineIndex);
            System.out.println(line);
            logger.writeLine(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
