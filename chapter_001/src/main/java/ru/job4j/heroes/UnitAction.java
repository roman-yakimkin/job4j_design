package ru.job4j.heroes;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Интерфейс - действие для юнита
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 19.04.2020
 * @version 1.0
 */
public interface UnitAction {
    public boolean isPossible();
    public String getName();
    public int getDamage();
    public Map<Unit, BiConsumer<Unit, Unit>> execute(List<Unit> targets);
    public void setOwner(Unit owner);
    public List<Unit> getPossibleTargets();
}
