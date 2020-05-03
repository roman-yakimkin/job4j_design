package ru.job4j.ioexam;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Работа с файловыми сущностями
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 03.05.2020
 * @version 1.0
 */
public class FileEntity {
    public static void main(String[] args) {
        File file1 = new File("./chapter_002/data/");
        System.out.println("The file entity " + file1.getAbsolutePath() + " is " + (file1.isDirectory() ? "directory" : "file"));
        File file2 = new File("./chapter_002/data/chat.log");
        System.out.println("The file entity " + file2.getAbsolutePath() + " is " + (file2.isDirectory() ? "directory" : "file"));

        Path path1 = Paths.get("./chapter_002/data/");
        System.out.println("The file entity " + path1.toAbsolutePath() + " is " + (Files.isDirectory(path1) ? "directory" : "file"));
        Path path2 = Paths.get("./chapter_002/data/chat.log");
        System.out.println("The file entity " + path2.toAbsolutePath() + " is " + (Files.isDirectory(path2) ? "directory" : "file"));
    }
}
