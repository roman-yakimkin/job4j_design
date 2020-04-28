package ru.job4j.chat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Класс для логирования в файл
 * @author Roman Yakimkin (r.yakmkin@yandex.ru)
 * @since 28.04.2020
 * @version 1.0
 */
public class FileLogger implements Logger {
    private String logFileName;

    public FileLogger(String logFileName) {
        this.logFileName = logFileName;
        File f = new File(logFileName);
        f.delete();
    }

    @Override
    public void writeLine(String line) {
        try (FileWriter fw = new FileWriter(logFileName, true)) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = Calendar.getInstance().getTime();
            fw.write(df.format(now) + " " + line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
