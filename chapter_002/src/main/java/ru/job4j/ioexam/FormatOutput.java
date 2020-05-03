package ru.job4j.ioexam;

import java.util.Locale;

/**
 * Эксперименты с форматированным выводом
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.05.2020
 * @version 1.0
 */
public class FormatOutput {
    public static void main(String[] args) {
        for (int i = 1; i < 101; i++) {
            double r = Math.sqrt(i);
            System.out.println("sqrt of " + i + " is " + r);
            System.out.format(Locale.ROOT, "Formatted sqrt of %d is %.3f %n", i, r);
        }
    }
}
