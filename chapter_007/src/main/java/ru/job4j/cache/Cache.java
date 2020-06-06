package ru.job4j.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Неблокирующий кэш
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 06.06.2020
 * @version 1.0
 */
public class Cache {
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public Base add(Base model) throws OptimisticException {
        return cache.put(model.id, model);

    }

    public Base update(Base model) throws OptimisticException {
        return cache.computeIfPresent(model.id, (k, v) -> {
            if (v.version != model.version) {
                throw new OptimisticException("The version has been changed");
            }
            model.version++;
            return model;
        });
    }

    public Base delete(Base model) throws OptimisticException {
        return cache.remove(model.id);
    }
}
