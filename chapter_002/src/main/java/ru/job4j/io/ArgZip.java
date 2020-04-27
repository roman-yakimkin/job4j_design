package ru.job4j.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Класс для работы с аргументами архива
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 27.04.2020
 * @version 1.0
 */
public class ArgZip {
    private final String[] args;

    private String directory;
    private String exclude = "";
    private String output;
    private final boolean lastParsingResult;

    public ArgZip(String[] args) {
        this.args = args;
        lastParsingResult = tryToParse();
    }

    private boolean tryToParse() {
        List<String> collectArgs = Arrays.asList(args);
        int indexDir = collectArgs.indexOf("-d");
        if (indexDir == -1 || indexDir == collectArgs.size() - 1) {
            return false;
        } else {
            Path dir = Paths.get(collectArgs.get(indexDir + 1));
            if (!Files.exists(dir) || !Files.isDirectory(dir)) {
                return false;
            }
            directory = collectArgs.get(indexDir + 1);
        }

        int indexOutput = collectArgs.indexOf("-o");
        if (indexOutput == -1 || indexOutput == collectArgs.size() - 1) {
            return false;
        } else {
            Path oFile = Paths.get(collectArgs.get(indexOutput + 1));
            output = collectArgs.get(indexOutput + 1);
        }

        int indexExclude = collectArgs.indexOf("-e");
        if (indexExclude != -1 || indexExclude != collectArgs.size() - 1) {
            exclude = collectArgs.get(indexExclude + 1);
        }
        return true;
    }

    public boolean valid() {
        return lastParsingResult;
    }

    public String directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }
}
