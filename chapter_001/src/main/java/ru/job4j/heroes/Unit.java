package ru.job4j.heroes;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс - боевая единица
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 19.04.2020
 * @version 1.0
 */
public interface Unit {
    public Player getPlayer();
    public void setPlayer(Player player);
    public void setBattleField(BattleField battleField);
    public List<UnitAction> getPossibleActions();
    public List<Unit> getAllyUnits();
    public List<Unit> getOppositeUnits();
    public Map<String, String> getUnitInfo();
    public int getHitPoints();
    public void setHitPoints(int hitPoints);
    public boolean isAlive();
    public int getPriority();
    public UnitState getUnitState();
    public void setUnitState(UnitState unitState);
}
