package ru.job4j.heroes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Действие для рукопашний схватки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 19.04.2020
 * @version 1.0
 */
public class MeleeAction implements UnitAction {
    private String name;
    private Unit owner;
    private int damage;

    public MeleeAction(String name, int damage) {
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
    public boolean isPossible() {
        return true;
    }

    @Override
    public void setOwner(Unit owner) {
        this.owner = owner;
    }

    @Override
    public Map<Unit, BiConsumer<Unit, Unit>> execute(List<Unit> targets) {
        Map<Unit, BiConsumer<Unit, Unit>> result = new HashMap<>();
        for (Unit unit : targets) {
            result.put(unit, (uSource, uTarget) -> uTarget.setHitPoints(uTarget.getHitPoints() - getDamage()));
        }
        return result;
    }

    @Override
    public List<Unit> getPossibleTargets() {
        return owner.getOppositeUnits();
    }
}
