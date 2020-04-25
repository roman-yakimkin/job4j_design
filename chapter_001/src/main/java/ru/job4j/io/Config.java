package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс для конфигурирования
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 25.04.2020
 * @version 1.0
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        values.clear();
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach((l) -> {
                try {
                    if (l.trim().charAt(0) == '#') {
                        return;
                    }
                    String[] parsed = l.trim().split("=", 2);
                    if (parsed.length == 2) {
                        String key = parsed[0].trim();
                        String value = parsed[1].trim();
                        if (key.length() > 0 && value.length() > 0) {
                            values.put(key, value);
                        }
                    }
                } catch (Exception ignored) { }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
