package jdbc;

import java.sql.*;
import java.util.Properties;

/**
 * Простой класс для чтения значений таблицы из базы данных
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.05.2020
 * @version 1.0
 */
public class SimpleReadData {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, props);
            System.out.println("Connected!");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select p.*, t.name as name_type from products p inner join product_types t on p.type_id = t.id order by p.id");
            while (rs.next()) {
                System.out.println(String.format("%d  %s  %s  %.2f",
                        rs.getInt("id"),
                        rs.getString("name_type"),
                        rs.getString("name"),
                        rs.getFloat("price"))
                );
            }
            rs.close();
            st.close();
7        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
