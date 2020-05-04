package ru.job4j.ioexam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Разные способы получения списка файлов
 * @author Roman Yakimkn (r.yakimkin@yandex.ru)
 * @since 03.05.2020
 * @version 1.0
 */
public class GetFilesList {
    public static void main(String[] args) throws IOException {
        File file = new File("./chapter_002/data/");
        String[] list1 = file.list();
        if (list1 != null) {
            for (String f : file.list()) {
                System.out.println(f);
            }
        }

        Stream<Path> list2 = Files.list(Paths.get("./chapter_002/data/"));
        for (Path f : list2.collect(Collectors.toList())) {
            System.out.println(f.getFileName());
        }

    }
}
