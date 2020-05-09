package ru.job4j.generic;

/**
 * Хранилище для пользователей
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.05.2020
 * @version 1.0
 */
public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
