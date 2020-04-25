package ru.job4j.heroes;

import java.util.List;

/**
 * Класс - игрок
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public class CustomPlayer implements Player {
    private String name;
    private DataInput dataInput;
    private Race race;

    public CustomPlayer(String name, Race race, DataInput dataInput) {
        this.name = name;
        this.race = race;
        this.dataInput = dataInput;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void assignUnit(Unit unit) {
        unit.setPlayer(this);
    }

    @Override
    public void assignArmy(List<Unit> units) {
        units.forEach(this::assignUnit);
    }

    @Override
    public Race getRace() {
        return race;
    }

    @Override
    public void setRace(Race race) {
        this.race = race;
    }
}
