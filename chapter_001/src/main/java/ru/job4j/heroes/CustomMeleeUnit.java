package ru.job4j.heroes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс - рукопашный юнит
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 21.04.2020
 * @version 1.0
 */
public class CustomMeleeUnit implements Unit {
    private Player player;
    private String name;
    private String unitType;
    private UnitState unitState = UnitState.NORMAL;
    private int hitPoints;
    private BattleField battleField;
    private UnitFactory unitFactory;
    private List<UnitAction> actions;
    private int priority = 50;

    public CustomMeleeUnit(UnitFactory unitFactory, String unitType, String name, int hitPoints, List<UnitAction> actions) {
        this.unitFactory = unitFactory;
        this.unitType = unitType;
        this.name = name;
        this.hitPoints = hitPoints;
        this.actions = actions;
        this.actions.forEach((ua) -> ua.setOwner(this));
    }

    public CustomMeleeUnit(UnitFactory unitFactory, String unitType, String name, int hitPoints, List<UnitAction> actions, Unit actionOwner) {
        this.unitFactory = unitFactory;
        this.unitType = unitType;
        this.name = name;
        this.hitPoints = hitPoints;
        this.actions = actions;
        this.actions.forEach((ua) -> ua.setOwner(actionOwner));
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
    }

    @Override
    public List<UnitAction> getPossibleActions() {
        return actions.stream().filter(UnitAction::isPossible).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getUnitInfo() {
        Map<String, String> result = new HashMap<>();
        result.put("type", unitFactory.getDisplayName(unitType));
        result.put("name", name);
        result.put("hit_points", String.valueOf(hitPoints));
        return result;
    }

    @Override
    public List<Unit> getAllyUnits() {
        return battleField.getFilteredUnits((u) -> (u.getPlayer().equals(player)));
    }

    @Override
    public List<Unit> getOppositeUnits() {
        return battleField.getFilteredUnits((u) -> (!u.getPlayer().equals(player)));
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public boolean isAlive() {
        return (hitPoints > 0);
    }

    @Override
    public UnitState getUnitState() {
        return unitState;
    }

    @Override
    public void setUnitState(UnitState unitState) {
        this.unitState = unitState;
    }

    @Override
    public int getPriority() {
        return unitState.priorityModifier(priority);
    }

    @Override
    public String toString() {
        String displayName = unitFactory.getDisplayName(unitType);
        String result;
        if (hitPoints > 0) {
           result = name + " (" + displayName + "), priority = " + getPriority() +  ", hit points: " + hitPoints;
        } else {
           result = name + " (" + displayName + ") - killed";
        }
        return result;
    }
}
