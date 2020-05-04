package ru.job4j.ioexam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Перемещение файла
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 03.05.2020
 * @version 1.0
 */
public class MoveFile {
    public static void main(String[] args) throws IOException {
        File newDir = new File("./chapter_002/data/new_dir");
        File movingFile = new File("./chapter_002/data/moving_file.txt");
        movingFile.createNewFile();
        FileWriter fw = new FileWriter(movingFile);
        fw.write("Hello world!");
        fw.close();
        newDir.mkdir();
        if (movingFile.exists() && newDir.exists()) {
            Path currentFile = Paths.get(movingFile.getAbsolutePath());
            Path newFile = Paths.get(newDir.getAbsolutePath() + "/moved_file.txt");
            Files.move(currentFile, newFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
