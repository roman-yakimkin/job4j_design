package ru.job4j.heroes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Класс - фабрика для генерации юнитов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public class CustomUnitFactory implements UnitFactory {

    public class UnitPrototype {
        String displayName;
        Function<String, Unit> createFunction;

        public UnitPrototype(String displayName, Function<String, Unit> createFunction) {
            this.displayName = displayName;
            this.createFunction = createFunction;
        }
    }

    private Map<String, UnitPrototype> prototypes = new HashMap<>();

    @Override
    public void addUnitPrototype(String prototypeName, String displayName, Function<String, Unit> createFunction) {
        prototypes.put(prototypeName, new UnitPrototype(displayName, createFunction));
    }

    @Override
    public String getDisplayName(String prototypeName) {
        return prototypes.get(prototypeName).displayName;
    }

    @Override
    public Optional<Unit> unitCreate(String prototypeName, String unitName) {
        return Optional.of(prototypes.get(prototypeName).createFunction.apply(unitName));
    }

}
