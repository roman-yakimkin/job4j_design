package ru.job4j.heroes;

import java.util.List;

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
