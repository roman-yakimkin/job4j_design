package ru.job4j.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс для обхода файлов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.04.2020
 * @version 1.0
 */
public class FileFinder implements FileVisitor<Path> {
    private String fileName = "";
    private List<String> results;
    private Predicate<Path> condition;

    public FileFinder(String fileName, Predicate<Path> condition, List<String> results) {
        this.fileName = fileName;
        this.condition = condition;
        this.results = results;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            results.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
