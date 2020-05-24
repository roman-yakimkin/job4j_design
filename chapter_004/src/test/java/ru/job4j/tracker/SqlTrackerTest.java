package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("id", "name"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void replaceItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("id", "name"));
            tracker.replace("id", new Item(null, "replaced"));
            assertThat(tracker.findByName("name").size(), is(0));
            assertThat(tracker.findByName("replaced").size(), is(1));
            assertThat(tracker.findById("id"), is(new Item("id", "replaced")));
        }
    }

    @Test
    public void deleteItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("id", "name"));
            assertThat(tracker.findByName("name").size(), is(1));
            tracker.delete("id");
            assertThat(tracker.findByName("name").size(), is(0));
        }
    }

    @Test
    public void findAllItems() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.deleteAll();
            tracker.add(new Item("1", "one"));
            tracker.add(new Item("2", "two"));
            tracker.add(new Item("3", "three"));
            List<Item> expected = List.of(
                    new Item("1", "one"),
                    new Item("2", "two"),
                    new Item("3", "three")
            );
            assertThat(tracker.findAll(), is(expected));
        }
    }
}