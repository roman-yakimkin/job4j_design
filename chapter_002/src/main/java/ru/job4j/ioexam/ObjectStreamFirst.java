package ru.job4j.ioexam;

import java.io.*;
import java.util.List;

/**
 * Эксперименты для работы с ObjectStream
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 03.05.2020
 * @version 1.0
 */
public class ObjectStreamFirst {
    public static void main(String[] args) {
        List<String> stList = List.of("First", "Second", "Third");
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./chapter_002/data/objects.dat")))) {
            out.writeObject(stList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./chapter_002/data/objects.dat")))) {
            List<String> readList = (List<String>) in.readObject();
            for (String el : readList) {
                System.out.println(el);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
