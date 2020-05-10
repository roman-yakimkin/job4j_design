package ru.job4j.collection;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для экспериментов с картой пользователей
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 10.05.2020
 * @version 1.0
 */
public class UserMap {
    public static void main(String[] args) {
        User first = new User("Иванов Петр Сергеевич", 2, new GregorianCalendar(1980, 1, 10));
        User second = new User("Иванов Петр Сергеевич", 2, new GregorianCalendar(1980, 1, 10));
        Map<User, Object> users = new HashMap<>();
        users.put(first, new Object());
        users.put(second, new Object());
        for (Map.Entry<User, Object> u : users.entrySet()) {
            System.out.println(u.getKey().hashCode() + " -> " + u.getValue());
        }
//        System.out.println(users.toString());
    }
}
