package ru.job4j.ioexam;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Чтение из файла с nio и FileChannel
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 04.05.2020
 * @version 1.0
 */
public class NIOFileReadSecond {
    public static void main(String[] args) {
        try (FileChannel chan = (FileChannel) Files.newByteChannel(Paths.get("./chapter_002/data/fileforscan.txt"))) {
            long fSize = chan.size();
            MappedByteBuffer buff = chan.map(FileChannel.MapMode.READ_ONLY, 0, fSize);
            for (int i = 0; i < fSize; i++) {
                System.out.print((char) buff.get());
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
