package ru.job4j.heroes;

import java.util.List;
import java.util.Map;

public class CustomAmmoShootUnit implements AmmoShootUnit {
    CustomMeleeUnit meleeUnit;
    int ammo;

    public CustomAmmoShootUnit(UnitFactory unitFactory, String unitType, String name, int hitPoints, List<UnitAction> actions, int ammo) {
        this.meleeUnit = new CustomMeleeUnit(unitFactory, unitType, name, hitPoints, actions, this);
        this.ammo = ammo;
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
    public Map<String, String> getUnitInfo() {
        Map<String, String> result = meleeUnit.getUnitInfo();
        result.put("ammo", String.valueOf(ammo));
        return result;
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
    public int ammoLeft() {
        return ammo;
    }

    @Override
    public void spentAmmo() {
        ammo--;
    }

    @Override
    public String toString() {
        if (meleeUnit.getHitPoints() > 0) {
            return meleeUnit.toString() + ", ammo: " + ammo;
        } else {
            return meleeUnit.toString();
        }
    }
}
