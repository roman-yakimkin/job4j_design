package ru.job4j.heroes;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Ввод данных для бота
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 21.04.2020
 * @version 1.0
 */
public class BotDataInput implements DataInput {
    @Override
    public UnitAction getUnitAction(BattleField battleField, Unit unit) {
        List<UnitAction> possibleActions = unit.getPossibleActions();
        Random r = new Random();
        int index = r.nextInt(possibleActions.size());
        return possibleActions.get(index);
    }

    @Override
    public List<Unit> getTargetsForAction(BattleField battleField, Unit unit, UnitAction action) {
        List<Unit> targets = action.getPossibleTargets();
        Random r = new Random();
        int index = r.nextInt(targets.size());
        return List.of(targets.get(index));
    }

    @Override
    public Race getRaceForPlayer(Player player, List<Race> races, Race oppositeRace) {
        Random r = new Random();
        List<Race> chosenRaces = races;
        if (oppositeRace != null) {
            chosenRaces = races.stream().filter((rc) -> (rc.getAttitude() != oppositeRace.getAttitude())).collect(Collectors.toList());
        }
        int index = r.nextInt(chosenRaces.size());
        return races.get(index);
    }

    @Override
    public Unit getUnitForAction(Player player, List<Unit> unitQueue) {
        Optional<Unit> unit = unitQueue.stream()
                .filter((u) -> (u.getHitPoints() > 0))
                .max(Comparator.comparing(Unit::getPriority));

        return unit.orElse(null);
    }
}
