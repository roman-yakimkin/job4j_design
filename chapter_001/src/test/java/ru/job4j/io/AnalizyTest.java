package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {
    @Test
    public void testUnavailable() {
        Analizy analizer = new Analizy();
        String source = "./data/source1.log";
        String target = "./data/target1.csv";
        analizer.unavailable(source, target);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(target)));
            List<String> lines = Files.readAllLines(Paths.get(target));
            assertThat(lines.size(), is(3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
