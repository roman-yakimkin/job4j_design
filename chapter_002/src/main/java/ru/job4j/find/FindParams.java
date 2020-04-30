package ru.job4j.find;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для работы с параметрами утилиты поиска файлов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.04.2020
 * @version 1.0
 */
public class FindParams {
    /**
     * Массив входных аргументов
     */
    private String[] args;
    /**
     * Начальная директория
     */
    private String initialDir;
    /**
     * Имя файла или маска для поиска
     */
    private String fileName;
    /**
     * Имя файла для вывода результата
     */
    private String outputFile;
    /**
     * Верные ли входные параметры
     */
    private boolean isParsedSuccessfully;

    public FindParams(String[] args) {
        this.args = args;
        tryToParse();
        isParsedSuccessfully = tryToParse();
    }

    /**
     * Попытка распознавания входных параметров
     * @return истина, если параметры распознаны
     */
    private boolean tryToParse() {
        boolean result = false;
        List<String> collectArgs = Arrays.asList(args);
        int indexDir = collectArgs.indexOf("-d");
        if (indexDir == -1 || indexDir == collectArgs.size() - 1) {
            return false;
        } else {
            Path dir = Paths.get(collectArgs.get(indexDir + 1));
            if (!Files.exists(dir) || !Files.isDirectory(dir)) {
                return false;
            }
            initialDir = collectArgs.get(indexDir + 1);
        }

        int indexOutput = collectArgs.indexOf("-o");
        if (indexOutput == -1 || indexOutput == collectArgs.size() - 1) {
            return false;
        } else {
            Path oFile = Paths.get(collectArgs.get(indexOutput + 1));
            outputFile = collectArgs.get(indexOutput + 1);
        }

        int indexFileName = collectArgs.indexOf("-n");
        if (indexFileName == -1 || indexFileName == collectArgs.size() - 1) {
            return false;
        } else {
            fileName = collectArgs.get(indexFileName + 1);
        }
        return true;
    }

    /**
     * Правильно ли распознаны параметры
     * @return истина, если параметры распознаны правильно
     */
    public boolean isValid() {
        return isParsedSuccessfully;
    }

    public String getInitialDir() {
        return initialDir;
    }

    public String getFileName() {
        return fileName;
    }

    public String getOutputFile() {
        return outputFile;
    }

    /**
     * Получить справочную информацию
     * @return справочная информация в текстовом виде
     */
    public String getHelp() {
        return "Usage: java find.jar [options...]" + System.lineSeparator()
                + "-d   initial directory" + System.lineSeparator()
                + "-n   file name or file mask" + System.lineSeparator()
                + "-o   file name to output results" + System.lineSeparator();
    }
}
