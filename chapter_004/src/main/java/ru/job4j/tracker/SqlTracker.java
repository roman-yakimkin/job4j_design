package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Трекер заявок с записью в БД
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.05.2020
 * @version 1.0
 */
public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
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

    /**
     * Генерация уникального ключа для заявки
     * @return сгенерированный уникальный ключ
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    @Override
    public Item add(Item item) {
        try {
            PreparedStatement st = cn.prepareStatement("INSERT INTO items VALUES (?, ?)");
            if (item.getId() == null) {
                item.setId(generateId());
            }
            st.setString(1, item.getId());
            st.setString(2, item.getName());
            st.executeUpdate();
            st.close();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean replace(String id, Item item) {
        try {
            PreparedStatement st = cn.prepareStatement("update items set name = ? where id = ?");
            st.setString(1, item.getName());
            st.setString(2, id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            PreparedStatement st = cn.prepareStatement("delete from items where id = ?");
            st.setString(1, id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from items");
            while (rs.next()) {
                result.add(new Item(rs.getString("id"), rs.getString("name")));
            }
            rs.close();
            st.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findByName(String key) {
        try {
            PreparedStatement st = cn.prepareStatement("select * from items where name = ?");
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            List<Item> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Item(rs.getString("id"), rs.getString("name")));
            }
            rs.close();
            st.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Item findById(String id) {
        try {
            PreparedStatement st = cn.prepareStatement("select * from items where id = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            Item result = null;
            if (rs.next()) {
               result = new Item(rs.getString("id"), rs.getString("name"));
            }
            rs.close();
            st.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
