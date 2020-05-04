package ru.job4j.ioexam;

import java.io.*;

/**
 * Посчитать количество байтов в файле
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.05.2020
 * @version 1.0
 */
public class ByteCount {
    public static void main(String[] args) throws IOException {
        try (
            InputStream inputStream = new FileInputStream("/home/roman/projects/result.zip");
            OutputStream outputStream = new FileOutputStream("/home/roman/projects/bytes.txt")
        ) {
            int[] countBytes = new int[256];
            int c;
            while ((c = inputStream.read()) != -1) {
                countBytes[c]++;
            }
            for (int i = 0; i < 256; i++) {
                String line = i + " -> " + countBytes[i] + System.lineSeparator();
                outputStream.write(line.getBytes());
            }
        }
    }
}
