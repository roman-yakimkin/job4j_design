package ru.job4j.heroes;

import java.util.List;

/**
 * Интерфейс - игрок
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 20.04.2020
 * @version 1.0
 */
public interface Player {
    public String getName();
    public void assignUnit(Unit unit);
    public void assignArmy(List<Unit> units);
    public Race getRace();
    public void setRace(Race race);
}
