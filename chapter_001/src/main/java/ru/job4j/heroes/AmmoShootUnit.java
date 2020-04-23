package ru.job4j.heroes;

/**
 * Интерфейс для стреляющего юнита
 */
public interface AmmoShootUnit extends Unit {
    public int ammoLeft();
    public void spentAmmo();
}
