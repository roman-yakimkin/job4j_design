package ru.job4j.ioexam;

import java.io.*;

/**
 * Пример работы с DataStream
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 03.05.2020
 * @version 1.0
 */
public class DataStreamFirst {
    public static void main(String[] args) {
        int[] numbers = new int[100];
        double[] roots = new double[100];
        for (int i = 1; i <= 100; i++) {
            numbers[i - 1] = i;
            roots[i - 1] = Math.sqrt(i);
        }
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("./chapter_002/data/osroots.dat")))) {
            for (int i = 0; i < 100; i++) {
                out.writeInt(numbers[i]);
                out.writeDouble(roots[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("./chapter_002/data/osroots.dat")))) {
            for (int i = 0; i < 100; i++) {
                int arg = in.readInt();
                double root = in.readDouble();
                System.out.printf("The Square Root of %d is %f \n", arg, root);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
