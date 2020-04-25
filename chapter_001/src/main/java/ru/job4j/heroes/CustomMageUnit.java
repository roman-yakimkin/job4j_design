package ru.job4j.heroes;

import java.util.List;
import java.util.Map;

/**
 * Класс - магический юнит
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public class CustomMageUnit implements Unit {
    /**
     * Данные рукопашного юнита для декорации
     */
    CustomMeleeUnit meleeUnit;

    public CustomMageUnit(UnitFactory unitFactory, String unitType, String name, int hitPoints, List<UnitAction> actions) {
        meleeUnit = new CustomMeleeUnit(unitFactory, unitType, name, hitPoints, actions, this);
    }

    @Override
    public Player getPlayer() {
        return meleeUnit.getPlayer();
    }

    @Override
    public void setPlayer(Player player) {
        meleeUnit.setPlayer(player);
    }

    @Override
    public void setBattleField(BattleField battleField) {
        meleeUnit.setBattleField(battleField);
    }

    @Override
    public List<UnitAction> getPossibleActions() {
        return meleeUnit.getPossibleActions();
    }

    @Override
    public List<Unit> getAllyUnits() {
        return meleeUnit.getAllyUnits();
    }

    @Override
    public List<Unit> getOppositeUnits() {
        return meleeUnit.getOppositeUnits();
    }

    @Override
    public Map<String, String> getUnitInfo() {
        return meleeUnit.getUnitInfo();
    }

    @Override
    public int getHitPoints() {
        return meleeUnit.getHitPoints();
    }

    @Override
    public void setHitPoints(int hitPoints) {
        meleeUnit.setHitPoints(hitPoints);
    }

    @Override
    public boolean isAlive() {
        return meleeUnit.isAlive();
    }

    @Override
    public int getPriority() {
        return meleeUnit.getPriority();
    }

    @Override
    public UnitState getUnitState() {
        return meleeUnit.getUnitState();
    }

    @Override
    public void setUnitState(UnitState unitState) {
        meleeUnit.setUnitState(unitState);
    }

    @Override
    public String toString() {
        return meleeUnit.toString();
    }
}
