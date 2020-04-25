package ru.job4j.heroes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Действие для дальнобойной атаки
 */
public class AmmoShootAction implements UnitAction {
    /**
     * Название действия
     */
    private String name;
    /**
     * Родительский юнит для действия
     */
    AmmoShootUnit owner;
    /**
     * Величина урона
     */
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

    /**
     * Расчет действия для целей
     * @param targets - список целей
     * @return - функции действий по целям
     */
    @Override
    public Map<Unit, BiConsumer<Unit, Unit>> execute(List<Unit> targets) {
        Map<Unit, BiConsumer<Unit, Unit>> result = new HashMap<>();
        for (Unit unit : targets) {
            result.put(unit, (uSource, uTarget) -> {
                uTarget.setHitPoints(uTarget.getHitPoints() - getDamage());
                ((AmmoShootUnit)uSource).spentAmmo();
            });
        }
        return result;
    }

    /**
     * Получить список возможных целей для действия
     * @return - список возможных целей
     */
    @Override
    public List<Unit> getPossibleTargets() {
        return owner.getOppositeUnits();
    }
}
