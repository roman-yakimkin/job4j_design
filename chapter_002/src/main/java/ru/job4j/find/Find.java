package ru.job4j.find;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс для поиска файлов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.04.2020
 * @version 1.0
 */
public class Find {
    public static void main(String[] args) {
        FindParams params = new FindParams(args);
        if (params.isValid()) {
            Predicate<Path> condition = ConditionFactory.condition(params);
            List<String> results = new ArrayList<>();
            try {
                Files.walkFileTree(Paths.get(params.getInitialDir().toString()), new FileFinder(params.getFileName(), condition, results));
                writeResultsToFile(params, results);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("You have inputted incorrect parameters" + System.lineSeparator() + params.getHelp());
        }
    }

    /**
     * Запись результатов в текстовый файл
     * @param params - Параметры запроса
     * @param files - список имен файлов - результатов
     */
    private static void writeResultsToFile(FindParams params, List<String> files) throws IOException {
        try (FileWriter fileWriter = new FileWriter(params.getOutputFile())) {
            fileWriter.write("Matches for " + params.getFileName() + ":" + System.lineSeparator());
            files.forEach((line) -> {
                try {
                    fileWriter.write(line + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
