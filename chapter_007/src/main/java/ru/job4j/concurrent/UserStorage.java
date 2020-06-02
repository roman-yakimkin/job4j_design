package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Потокобезопасный класс для хранения пользователей
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.06.2020
 * @version 1.0
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return (users.put(user.id, user) == null);
    }

    public synchronized boolean update(User user) {
        return (users.put(user.id, user) != null);
    }

    public synchronized boolean delete(User user) {
        return (users.remove(user.id) != null);
    }

    public synchronized boolean transferTo(int fromId, int toId, int amount) {
        if (users.get(fromId) == null || users.get(toId) == null) {
            return false;
        }
        User fromUser = users.get(fromId);
        if (fromUser.amount < amount) {
            return false;
        }
        User toUser = users.get(toId);
        toUser.amount += amount;
        fromUser.amount -= amount;
        return (update(fromUser) && update(toUser));
    }


    public static class User {
        private final int id;
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }
    }
}
