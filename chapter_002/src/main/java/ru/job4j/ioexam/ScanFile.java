package ru.job4j.ioexam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Сканирование данных из файла
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.05.2020
 * @version 1.0
 */
public class ScanFile {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream("/home/roman/projects/job4j_design/chapter_002/data/fileforscan.txt"));
        scan.useDelimiter("[\\s,\\.]+");
        while (scan.hasNext()) {
            System.out.println(scan.next());
        }
    }
}
