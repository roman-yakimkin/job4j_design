package ru.job4j.wget;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Программа для скачивания файла с указанием скорости в килобайтах в секунду
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 31.05.2020
 * @version 1.0
 */
public class Wget {

    private int kBytesPerSec;
    private String inputFilePath;
    private String outputDir;

    private boolean parseParams(String[] args) {
        if (args.length != 3) {
            System.out.println("This program should have 3 arguments");
            return false;
        }
        inputFilePath = args[0];
        try {
            kBytesPerSec = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        Path od = Paths.get(args[2]);
        if (Files.exists(od) && Files.isDirectory(od)) {
            outputDir = args[2];
        } else {
            System.out.println("The output directory does not exist");
            return false;
        }
        return true;
    }

    private String getInputFileName() {
        Path p = Paths.get(inputFilePath);
        return p.getFileName().toString();
    }

    private String getHelpInfo() {
        return "Usage: java wget.jar url_src speed dir_dst" + System.lineSeparator()
                + "url_src - url of the source file" + System.lineSeparator()
                + "speed - speed in KBytes per sec" + System.lineSeparator()
                + "dir_dst - the directory where the file will be saved" + System.lineSeparator();

    }

    public static void main(String[] args) {
        Wget wget = new Wget();
        if (wget.parseParams(args)) {
            try (BufferedInputStream in = new BufferedInputStream(new URL(wget.inputFilePath).openStream())) {
                FileOutputStream os = new FileOutputStream(wget.outputDir + '/' + wget.getInputFileName());
                byte[] dataBuffer = new byte[1024];
                long timeStart = System.currentTimeMillis();
                int bytesRead = in.read(dataBuffer, 0, 1024);
                int bytesReadInSec = bytesRead;
                long bytesReadAll = bytesRead;
                while (bytesRead != -1) {
                    bytesRead = in.read(dataBuffer, 0, 1024);
                    if (bytesRead != -1) {
                        os.write(dataBuffer, 0, bytesRead);
                    }
                    bytesReadInSec += bytesRead;
                    bytesReadAll += bytesRead;
                    if (bytesReadInSec >= wget.kBytesPerSec * 1024) {
                        long timeEnd = System.currentTimeMillis();
                        long interval = timeEnd - timeStart;
                        if (interval < 1000) {
                            Thread.sleep(1000 - interval);
                            System.out.format("\rLoading : %.2f Kb downloaded", (float)(bytesReadAll / 1024));
                        }
                        timeStart = System.currentTimeMillis();
                        bytesReadInSec = 0;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            System.out.println(wget.getHelpInfo());
        }
    }
}
