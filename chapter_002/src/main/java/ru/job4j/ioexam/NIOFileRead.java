package ru.job4j.ioexam;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Чтение файла средствами nio
 * @author Roman Yakimkn (r.yakimkin@yandex.ru)
 * @since 04.05.2020
 * @version 1.0
 */
public class NIOFileRead {
    public static void main(String[] args) {
        int count;
        Path path = null;
        try {
            path = Paths.get("./chapter_002/data/fileforscan.txt");
        } catch (InvalidPathException e) {
            e.printStackTrace();
            return;
        }

        try (SeekableByteChannel chan = Files.newByteChannel(path)) {
            ByteBuffer buff = ByteBuffer.allocate(128);
            do {
                count = chan.read(buff);
                if (count != -1) {
                    buff.rewind();
                    for (int i = 0; i < count; i++) {
                        System.out.println((char) buff.get());
                    }
                }
            } while (count != -1);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
