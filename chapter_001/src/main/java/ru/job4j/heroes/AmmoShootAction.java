package ru.job4j.heroes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Действие для дальнобойной атаки
 */
public class AmmoShootAction implements UnitAction {
    private String name;
    AmmoShootUnit owner;
    private int damage;

    public AmmoShootAction(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return owner.getUnitState().damageModifier(damage);
    }

    @Override
    public void setOwner(Unit owner) {
        this.owner = (AmmoShootUnit) owner;
    }

    @Override
    public boolean isPossible() {
        return (owner.ammoLeft() > 0);
    }

    @Override
    public Map<Unit, Consumer<Unit>> execute(List<Unit> targets) {
        Map<Unit, Consumer<Unit>> result = new HashMap<>();
        for (Unit unit : targets) {
            result.put(unit, (u) -> u.setHitPoints(u.getHitPoints() - getDamage()));
        }
        return result;
    }

    @Override
    public List<Unit> getPossibleTargets() {
        return owner.getOppositeUnits();
    }
}
