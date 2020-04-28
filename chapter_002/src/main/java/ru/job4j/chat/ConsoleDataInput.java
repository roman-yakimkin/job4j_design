package ru.job4j.chat;

import java.io.*;

/**
 * Ввод данных с консоли
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 28.04.2020
 * @version 1.0
 */
public class ConsoleDataInput implements DataInput {
    @Override
    public String getInputPhrase() {
        System.out.print("Введите что-нибудь: ");
        BufferedReader br;
        String line = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
