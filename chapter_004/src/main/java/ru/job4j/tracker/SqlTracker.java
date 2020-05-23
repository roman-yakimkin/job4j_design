package ru.job4j.tracker;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Трекер заявок с записью в БД
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.05.2020
 * @version 1.0
 */
public class SqlTracker implements Store {
    private Connection cn;
    private Logger logger;

    public SqlTracker(Logger logger) {
        this.logger = logger;
    }

    public SqlTracker(Connection cn, Logger logger) {
        this.cn = cn;
        this.logger = logger;
    }

    public SqlTracker() { }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        PreparedStatement st = null;
        try {
            if (item.getId() == null) {
                st = cn.prepareStatement("INSERT INTO items VALUES (CAST (nextval('seq_items') as VARCHAR), ?)");
                st.setString(1, item.getName());
            } else {
                st = cn.prepareStatement("INSERT INTO items VALUES (?, ?)");
                st.setString(1, item.getId());
                st.setString(2, item.getName());
            }
            boolean isAdded = (st.executeUpdate() > 0);
            if (isAdded) {
                return item;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean replace(String id, Item item) {
        try (PreparedStatement st = cn.prepareStatement("update items set name = ? where id = ?")) {
            st.setString(1, item.getName());
            st.setString(2, id);
            return (st.executeUpdate() > 0);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try (PreparedStatement st = cn.prepareStatement("delete from items where id = ?")) {
            st.setString(1, id);
            return (st.executeUpdate() > 0);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public void deleteAll() {
        try {
            PreparedStatement st = cn.prepareStatement("delete from items");
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<Item> findAll() {
//        for (int i = 0; i < 1000; i++) {
//            for (int j = 0; j < 1000; j++) {
//                String st = new String("Test String Test String Test String");
//                st = null;
//            }
//        }
        List<Item> result = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery("select * from items")) {
                while (rs.next()) {
                    result.add(new Item(rs.getString("id"), rs.getString("name")));
                }
            }
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Item> findByName(String key) {
        try (PreparedStatement st = cn.prepareStatement("select * from items where name = ?")) {
            st.setString(1, key);
            try (ResultSet rs = st.executeQuery()) {
                List<Item> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new Item(rs.getString("id"), rs.getString("name")));
                }
                return result;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Item findById(String id) {
        try (PreparedStatement st = cn.prepareStatement("select * from items where id = ?")) {
            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                Item result = null;
                if (rs.next()) {
                    result = new Item(rs.getString("id"), rs.getString("name"));
                }
                return result;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
