package ru.job4j.heroes;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Интерфейс - раса игрока
 * @author Roman Yakimkin (r.yaimkin@yandex.ru)
 * @since 19.04.2020
 * @version 1.0
 */
public interface Race {
    public RaceAttitude getAttitude();
    public String getName();
    public List<Unit> createArmy(Map<String, List<String>> unitInfo);
    public void setDefaultArmyStructure(Map<String, List<String>> unitInfo);
    public List<Unit> createDefaultArmy();
}
