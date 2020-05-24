package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация простого кэша
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 24.05.2020
 * @version 1.0
 * @param <K> - тип аргумента ключа
 * @param <V> - тип аргумента значения
 */
public class SimpleFileCache implements Cache<String, String> {

    private Map<String, SoftReference<String>> files = new HashMap<>();
    private String dirName;

    public SimpleFileCache(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public String get(String key) {
        SoftReference<String> value = files.get(key);
        if (value == null) {
            String content = "";
            try {
                content = Files.readString(Paths.get(dirName + File.separator + key));
            } catch (IOException e) {
                e.printStackTrace();
            }
            value = new SoftReference<>(content);
            files.put(key, value);
        }
        return value.get();

    }
}
