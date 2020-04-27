package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для сканирования директорий
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 27.04.2020
 * @version 1.0
 */
public class Search {
    public static void main(String[] args) throws IOException {
        List<String> results = search(Paths.get("."), "xml");
        results.forEach(System.out::println);
    }

    public static List<String> search(Path root, String ext) throws IOException {
        List<String> results = new ArrayList<>();
        Files.walkFileTree(root, new PrintFiles(ext, results));
        return results;
    }
}