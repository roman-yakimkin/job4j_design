package ru.job4j.heroes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Класс - поле боя
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public class HeroesBattleField implements BattleField {
    List<Unit> units = new ArrayList<>();

    @Override
    public void addUnit(Unit unit) {
        unit.setBattleField(this);
        units.add(unit);
    }

    @Override
    public void addArmy(List<Unit> units) {
        units.forEach((u) -> u.setBattleField(this));
        this.units.addAll(units);
    }

    @Override
    public List<Unit> getAllyUnitsFor(Unit unit) {
        return getFilteredUnits((u) -> (u.getPlayer().equals(unit.getPlayer())));
    }

    @Override
    public List<Unit> getOppositeUnitsFor(Unit unit) {
        return getFilteredUnits((u) -> (!u.getPlayer().equals(unit.getPlayer())));
    }

    @Override
    public List<Unit> getFilteredUnits(Predicate<Unit> filter) {
        return units.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public boolean allUnitsDead(Player player) {
        return units.stream().noneMatch((u) -> (u.getPlayer().equals(player) && u.getHitPoints() > 0));
    }
}
