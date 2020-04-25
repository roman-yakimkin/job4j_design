package ru.job4j.heroes;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Вывод данных на консоль
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public class ConsoleDataOutput implements DataOutput {

    /**
     * Отображение поля боя
     * @param battleField - поле боя
     * @param players - массив игроков
     */
    @Override
    public void outputBattleField(BattleField battleField, List<Player> players) {
        players.forEach((p) -> outputArmy(battleField, p));
    }

    /**
     * Отобразить юнит
     * @param unit - отображаемый юнит
     */
    @Override
    public void outputUnit(Unit unit) {
        Map<String, String> unitInfo = unit.getUnitInfo();
        System.out.println(unit.toString());
    }

    /**
     * Отобразить армию игрока
     * @param battleField - поле боя
     * @param player - игрок
     */
    @Override
    public void outputArmy(BattleField battleField, Player player) {
        System.out.println("Армия игрока " + player.getName() + " ==================");
        battleField.getFilteredUnits((u) -> (u.getPlayer().equals(player))).forEach(this::outputUnit);
    }

    /**
     * Отобразить действие
     * @param subject - юнит, производящий дейстие
     * @param action - действие
     * @param targets - юниты - цели действия
     */
    @Override
    public void outputAction(Unit subject, UnitAction action, List<Unit> targets) {
        Map<String, String> subjectInfo = subject.getUnitInfo();
        System.out.println("===");
        String result = subjectInfo.get("name") + " производит " + action.getName();
        if (targets.size() == 1) {
            result += ", цель: ";
        } else {
            result += ", цели: ";
        }
        StringJoiner joiner = new StringJoiner(", ");
        for (Unit target : targets) {
            Map<String, String> unitInfo = target.getUnitInfo();
            joiner.add(unitInfo.get("name"));
        }
        result += joiner.toString();
        System.out.println(result);
        System.out.println("===");
    }

    /**
     * Отобразить сообщение
     * @param message - отображаемое сообщение
     */
    @Override
    public void outputMessage(String message) {
        System.out.println(message);
    }
}
