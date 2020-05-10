package ru.job4j.collection;

import java.util.Calendar;
import java.util.Objects;

/**
 * Реализация модели User
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 09.05.2020
 * @version 1.0
 */
public class User {
    private String name;
    private int children = 0;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
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
        return getChildren() == user.getChildren()
                && Objects.equals(getName(), user.getName())
                && Objects.equals(getBirthday(), user.getBirthday());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
