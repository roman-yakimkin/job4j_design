package ru.job4j.find;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * Фабрика для получения функции отфильтровки файлов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.05.2020
 * @version 1.0
 */
public class ConditionFactory {
    /**
     * Получить функцию для отфильтровки файлов
     * @param findParams - аргументы
     * @return - функция
     */
    public static Predicate<Path> condition(FindParams findParams) {
        Predicate<Path> result = p -> true;
        String type = findParams.getSearchType();
        switch (type) {
            case "-n" :
                String s = preparePattern(findParams.getFileName());
                result = p -> p.toFile().getName().matches(s);
                break;
            case "-r" :
                result = p -> p.toFile().getName().matches(findParams.getFileName());
                break;
            default:
                break;
        }
        return result;
    }

    private static String preparePattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '*') {
                sb.append(".*");
            } else if (c == '.') {
                sb.append("\\.");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
