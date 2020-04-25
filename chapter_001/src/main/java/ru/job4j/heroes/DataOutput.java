package ru.job4j.heroes;

import java.util.List;

/**
 * Интерфейс для вывода данных
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 20.04.2020
 * @verson 1.0
 */
public interface DataOutput {
    public void outputBattleField(BattleField battleField, List<Player> players);
    public void outputArmy(BattleField battleField, Player player);
    public void outputUnit(Unit unit);
    public void outputAction(Unit subject, UnitAction action, List<Unit> targets);
    public void outputMessage(String message);
}
