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
        if (args.length == 0) {
            throw new IllegalStateException("Вы не указали параметры для запуска данной программы");
        }
        if (args.length == 1) {
            throw new IllegalStateException("Вы не указали требуемое расширение для фильтиации файлов");
        }
        Path root = Paths.get(args[0]);
        if (!Files.exists(root)) {
            throw new IllegalStateException(String.format("Not exist %s", root.toAbsolutePath().toString()));
        }
        if (!Files.isDirectory(root)) {
            throw new IllegalStateException(String.format("Not directory %s", root.toAbsolutePath().toString()));
        }
        String ext = args[1];
        List<String> results = search(root, ext);
        results.forEach(System.out::println);
    }

    public static List<String> search(Path root, String ext) throws IOException {
        List<String> results = new ArrayList<>();
        Files.walkFileTree(root, new PrintFiles(ext, results));
        return results;
    }
}