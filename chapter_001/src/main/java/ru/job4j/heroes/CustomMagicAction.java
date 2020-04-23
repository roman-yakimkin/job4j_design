package ru.job4j.heroes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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
    public Map<Unit, Consumer<Unit>> execute(List<Unit> targets) {
        Map<Unit, Consumer<Unit>> result = new HashMap<>();
        for (Unit unit : targets) {
            result.put(unit, (u) -> u.setUnitState(newState));
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
