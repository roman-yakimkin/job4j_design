package ru.job4j.heroes;

import java.util.List;

/**
 * Интерфейс для ввода данных
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 20.04.2020
 * @version 1.0
 */
public interface DataInput {
    public UnitAction getUnitAction(BattleField battleField, Unit unit);
    public List<Unit> getTargetsForAction(BattleField battleField, Unit unit, UnitAction action);
    public Race getRaceForPlayer(Player player, List<Race> races, Race oppositeRace);
    public Unit getUnitForAction(Player player, List<Unit> unitQueue);
}
