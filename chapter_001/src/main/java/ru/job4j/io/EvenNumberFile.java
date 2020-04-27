package ru.job4j.io;

import java.io.FileInputStream;

/**
 *  Класс для определения нечетных чисел
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 24.04.2020
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int numb = Integer.parseInt(line);
                System.out.println(line + (numb % 2 == 0 ? " - четное" : " - нечетное"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
