package ru.job4j.ioexam;

import java.util.Scanner;

/**
 * Получение со сканнера набора целых чисел
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 02.05.2020
 */
public class ScanInt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("1.2.3.4.5.6.7.8.9.10");
        scanner.useDelimiter("\\.");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        scanner.close();
    }
}

