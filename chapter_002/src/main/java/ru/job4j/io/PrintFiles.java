package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для печати имен файлов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 27.04.2020
 * @version 1.0
 */
public class PrintFiles implements FileVisitor<Path> {
    private String fileExt = "";
    private List<String> results;

    public PrintFiles(String fileExt, List<String> results) {
        this.fileExt = fileExt;
        this.results = results;
    }

    public PrintFiles() {
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean isValidFile = true;
        if (fileExt.length() > 0) {
            String[] fileFragments = file.getFileName().toString().split("\\.");
            if (!fileFragments[fileFragments.length - 1].equals(fileExt)) {
                isValidFile = false;
            }
        }
        if (isValidFile) {
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
