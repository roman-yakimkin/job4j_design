package ru.job4j.io;

import java.io.*;

/**
 * Класс - анализ файла логов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 26.04.2020
 * @version 1.0
 */
public class Analizy {

    private boolean isTypeUnavailable(int type) {
        return (type == 400 || type == 500);
    }

    public void unavailable(String source, String target) {
        try {
            int prevType = 0, currentType = 0;
            String timeBegin = "", timeEnd = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
            PrintWriter writer = new PrintWriter(new FileOutputStream(target));
            String line;
            String[] logElements = new String[2];
            while ((line = reader.readLine()) != null) {
                logElements = line.split(" ");
                currentType = Integer.parseInt(logElements[0]);
                if (isTypeUnavailable(currentType) && !isTypeUnavailable(prevType)) {
                    timeBegin = logElements[1];
                }
                if (!isTypeUnavailable(currentType) && isTypeUnavailable(prevType)) {
                    timeEnd = logElements[1];
                    writer.println(timeBegin + ";" + timeEnd);
                    timeBegin = "";
                    timeEnd = "";
                }
                prevType = currentType;
            }
            if (isTypeUnavailable(currentType)) {
                writer.println(timeBegin + ";" + logElements[1]);
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
