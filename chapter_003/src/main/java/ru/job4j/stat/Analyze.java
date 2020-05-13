package ru.job4j.stat;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс для сбора статистики
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 13.05.2020
 * @version 1.0
 */
public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        Set<Integer> idsCurrent = current.stream().map((u) -> (u.id)).collect(Collectors.toSet());
        Set<Integer> idsPrevious = previous.stream().map((u) -> (u.id)).collect(Collectors.toSet());

        Set<Integer> idsCurrent1 = new HashSet<>(idsCurrent);
        idsCurrent1.removeAll(idsPrevious);
        int added = idsCurrent1.size();

        Set<Integer> idsPrevious1 = new HashSet<>(idsPrevious);
        idsPrevious1.removeAll(idsCurrent);
        int deleted = idsPrevious1.size();

        int changed = 0;
        for (User user : current) {
            changed += previous.stream().filter((u) -> (u.id == user.id && !u.name.equals(user.name))).count();
        }
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
