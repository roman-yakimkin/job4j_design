package ru.job4j.tracker;

/**
 * Тестовый ввод данных
 */
public class StubInput implements Input {

    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String askStr(String question) {
        return answers[position++];
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        int value = askInt(question);
        if (value < 0 || value > max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", value, max));
        }
        return value;
    }
}
