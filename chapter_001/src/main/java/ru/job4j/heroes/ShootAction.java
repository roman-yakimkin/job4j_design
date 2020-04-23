package ru.job4j.heroes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Удар на расстоянии без расходования боеприпасов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 23.04.2020
 * @version 1.0
 */
public class ShootAction implements UnitAction {
    private String name;
    Unit owner;
    private int damage;

    public ShootAction(String name, int damage) {
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
        this.owner = owner;
    }

    @Override
    public boolean isPossible() {
        return true;
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
