package ru.job4j.heroes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

/**
 * Класс - магическое действие
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public class CustomMagicAction implements UnitAction {
    private String name;
    private Unit owner;
    private UnitState newState;
    private Function<Unit, List<Unit>> possibleTargets;

    public CustomMagicAction(String name, UnitState newState, Function<Unit, List<Unit>> possibleTargets) {
        this.name = name;
        this.newState = newState;
        this.possibleTargets = possibleTargets;
    }

    @Override
    public boolean isPossible() {
        return !getPossibleTargets().isEmpty();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public Map<Unit, BiConsumer<Unit, Unit>> execute(List<Unit> targets) {
        Map<Unit, BiConsumer<Unit, Unit>> result = new HashMap<>();
        for (Unit unit : targets) {
            result.put(unit, (uSource, uTarget) -> uTarget.setUnitState(newState));
        }
        return result;
    }

    @Override
    public void setOwner(Unit owner) {
        this.owner = owner;
    }

    @Override
    public List<Unit> getPossibleTargets() {
        return possibleTargets.apply(owner);
    }
}
