package ru.job4j.stat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для сбора статистики
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 13.05.2020
 * @version 1.0
 */
public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        int added = 0, changed = 0, deleted = 0;
        Map<Integer, User> users = previous.stream().collect(Collectors.toMap(u -> u.id, u -> u));
        for (User u : current) {
            User prev = users.put(u.id, u);
            if (prev == null) {
                added++;
            } else if (!prev.name.equals(u.name)) {
               changed++;
            }
            users.remove(u.id);
        }
        deleted = users.size();
        return new Info(added, changed, deleted);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }
}
