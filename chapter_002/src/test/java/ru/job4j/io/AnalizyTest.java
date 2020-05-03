package ru.job4j.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUnavailableCount() throws IOException {
        Analizy analizer = new Analizy();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            List.of("200 10:56:01", "200 10:57:01", "400 10:58:01", "200 10:59:01", "500 11:01:02", "200 11:02:02",
                    "200 11:03:02", "200 11:04:02", "200 11:05:02", "200 11:06:02", "400 11:07:02", "500 11:08:02").forEach(out::println);
        }
        analizer.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(target)));
            List<String> lines = Files.readAllLines(Paths.get(target.getAbsolutePath()));
            assertThat(lines.size(), is(3));
            boolean isEqual = lines.equals(List.of("10:58:01;10:59:01", "11:01:02;11:02:02", "11:07:02;11:08:02"));
            assertThat(isEqual, is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnavailableContains() throws IOException {
        Analizy analizer = new Analizy();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            List.of("200 10:56:01", "200 10:57:01", "400 10:58:01", "200 10:59:01", "500 11:01:02", "200 11:02:02",
                    "200 11:03:02", "200 11:04:02", "200 11:05:02", "200 11:06:02", "400 11:07:02", "500 11:08:02").forEach(out::println);
        }
        analizer.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(target)));
            List<String> lines = Files.readAllLines(Paths.get(target.getAbsolutePath()));
            String result = "11:01:02;11:02:02";
            assertThat(lines.contains(result), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnavailablePosition() throws IOException {
        Analizy analizer = new Analizy();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            List.of("200 10:56:01", "200 10:57:01", "400 10:58:01", "200 10:59:01", "500 11:01:02", "200 11:02:02",
                    "200 11:03:02", "200 11:04:02", "200 11:05:02", "200 11:06:02", "400 11:07:02", "500 11:08:02").forEach(out::println);
        }
        analizer.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(target)));
            List<String> lines = Files.readAllLines(Paths.get(target.getAbsolutePath()));
            String result = "11:01:02;11:02:02";
            assertThat(lines.get(1), is(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
