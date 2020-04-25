package ru.job4j.heroes;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс для поля боя
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 20.04.2020
 * @version 1.0
 */
public interface BattleField {
    public void addUnit(Unit unit);
    public void addArmy(List<Unit> units);
    public List<Unit> getAllyUnitsFor(Unit unit);
    public List<Unit> getOppositeUnitsFor(Unit unit);
    public List<Unit> getFilteredUnits(Predicate<Unit> filter);
    public boolean allUnitsDead(Player player);
}
