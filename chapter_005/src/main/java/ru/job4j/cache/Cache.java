package ru.job4j.cache;

/**
 * Интерфейс абстрактного кэша
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 24.05.2020
 * @version 1.0
 */
public interface Cache<K, V> {
    public V get(K key);
}
