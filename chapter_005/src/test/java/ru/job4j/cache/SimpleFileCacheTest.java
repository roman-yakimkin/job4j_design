package ru.job4j.cache;

import com.mchange.util.impl.SoftReferenceObjectCache;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleFileCacheTest {

    @Test
    public void firstTimeLoadFromFile() throws IOException {
        String content = "Beatles" + System.lineSeparator() + "Rolling Stones" + System.lineSeparator() + "Doors" + System.lineSeparator() + "Pink Floyd";
        File file = File.createTempFile("groups", ".txt");
        Files.write(file.toPath(), content.getBytes());

        String absPath = file.getAbsolutePath();
        String temporaryDir = absPath.substring(0, absPath.lastIndexOf(File.separator));
        SimpleFileCache fc = new SimpleFileCache(temporaryDir);
        assertThat(fc.get(file.getName()), is(content));
    }

    @Test
    public void secondTimeLoadFromCache() throws IOException {
        String oldGroups = "Beatles" + System.lineSeparator() + "Rolling Stones" + System.lineSeparator() + "Doors" + System.lineSeparator() + "Cream";
        File file = File.createTempFile("groups", ".txt");
        Files.write(file.toPath(), oldGroups.getBytes());

        String absPath = file.getAbsolutePath();
        String temporaryDir = absPath.substring(0, absPath.lastIndexOf(File.separator));

        SimpleFileCache fc = new SimpleFileCache(temporaryDir);
        assertThat(fc.get(file.getName()), is(oldGroups));

        String newGroups = "Oasis" + System.lineSeparator() + "Muse" + System.lineSeparator() + "Franz Ferdinand";
        Files.write(file.toPath(), newGroups.getBytes());

        assertThat(fc.get(file.getName()), is(oldGroups));
    }
}
