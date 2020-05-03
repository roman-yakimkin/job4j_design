package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Класс для отбора файлов с вычетом исключений
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 27.04.2020
 * @version 1.0
 */
public class ExcludeFiles implements FileVisitor<Path> {
    private String except;
    private List<File> results;

    public ExcludeFiles(String except, List<File> results) {
        this.except = except;
        this.results = results;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        PathMatcher pm = fs.getPathMatcher("glob:*" + except);
        if (!pm.matches(file)) {
            results.add(file.toFile());
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