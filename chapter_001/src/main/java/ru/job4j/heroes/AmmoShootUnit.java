package ru.job4j.heroes;

/**
 * Интерфейс для стреляющего юнита
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 20.04.2020
 * @vestion 1.0
 */
public interface AmmoShootUnit extends Unit {
    public int ammoLeft();
    public void spentAmmo();
}
