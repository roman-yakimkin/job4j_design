package ru.job4j.ioexam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Способы удаления файла
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 03.05.2020
 * @version 1.0
 */
public class DeleteFile {
    public static void main(String[] args) throws IOException {
        File file = new File("./chapter_002/data/file-to-remove.txt");
        if (file.createNewFile() || file.exists()) {
            file.delete();
        }

        Path path = Paths.get("./chapter_002/data/file-to-remove-path.txt");
        try {
            Files.createFile(path);
        } finally {
            if (Files.exists(path)) {
                Files.delete(path);
            }
        }
    }
}
