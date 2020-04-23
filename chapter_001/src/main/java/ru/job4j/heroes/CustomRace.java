package ru.job4j.heroes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class CustomRace implements Race {
    private String name;
    private RaceAttitude attitude;
    private UnitFactory unitPrototypes;
    private Map<String, List<String>> defaultArmyStructure = new HashMap<>();

    public CustomRace(String name, RaceAttitude attitude, UnitFactory unitPrototypes) {
        this.name = name;
        this.attitude = attitude;
        this.unitPrototypes = unitPrototypes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public RaceAttitude getAttitude() {
        return this.attitude;
    }

    @Override
    public List<Unit> createArmy(Map<String, List<String>> unitInfo) {
        List<Unit> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : unitInfo.entrySet()) {
            String unitType = entry.getKey();
            for (String unitName : entry.getValue()) {
                var unit = unitPrototypes.unitCreate(unitType, unitName);
                unit.ifPresent(result::add);
            }
        }
        return result;
    }

    @Override
    public void setDefaultArmyStructure(Map<String, List<String>> unitInfo) {
        this.defaultArmyStructure = unitInfo;
    }

    @Override
    public List<Unit> createDefaultArmy() {
        return createArmy(defaultArmyStructure);
    }
}
