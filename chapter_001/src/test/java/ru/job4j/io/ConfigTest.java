package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_without_comments.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertNull(config.value("mail"));
    }

    @Test
    public void whenPairContainsSpaces() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
    }
}
