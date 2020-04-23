package ru.job4j.heroes;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Интерфейс для фабрики юнитов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public interface UnitFactory {
    public Optional<Unit> unitCreate(String prototypeName, String unitName);
    public void addUnitPrototype(String prototypeName, String displayName, Function<String, Unit> createFunction);
    public String getDisplayName(String prototypeName);
}
