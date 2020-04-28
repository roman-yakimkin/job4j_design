package ru.job4j.chat;

import java.io.File;
import java.util.Map;

/**
 * Класс для реализации консольного чата
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 28.04.2020
 * @version 1.0
 */
public class ConsoleChat {
    private DataInput dataInput;
    private ChatState currentState;
    private Logger logger;

    private ChatState interlocutorState;
    private ChatState listenerState;
    private ChatState exitState;

    public ConsoleChat(String answersFileName, DataInput dataInput, Logger logger) {
        this.dataInput = dataInput;
        this.logger = logger;
        interlocutorState = new InterlocutorState(answersFileName, logger);
        listenerState = new ListenerState();
        exitState = new ExitState();
        currentState = interlocutorState;
    }

    private ChatState selectNewState(String inputPhrase) {
        if (inputPhrase == null) {
            return exitState;
        }
        Map<String, ChatState> states = Map.of("стоп", listenerState, "продолжить", interlocutorState, "закончить", exitState);
        for (Map.Entry<String, ChatState> state : states.entrySet()) {
            if (inputPhrase.toLowerCase().equals(state.getKey())) {
                return state.getValue();
            }
        }
        return currentState;
    }

    public void doAction() {
        String inputPhrase = dataInput.getInputPhrase();
        if (inputPhrase != null) {
            logger.writeLine(inputPhrase);
            currentState = selectNewState(inputPhrase);
            currentState.doAction();
        }
    }

    public void run() {
        while (currentState != exitState) {
            doAction();
        }
    }

    public static void main(String[] args) {
        DataInput dataInput = new ConsoleDataInput();
        Logger logger = new FileLogger("/home/roman/projects/job4j_design/chapter_002/data/chat.log");
        ConsoleChat chat = new ConsoleChat("/home/roman/projects/job4j_design/chapter_002/data/chat_answers.txt", dataInput, logger);
        chat.run();
    }
}
