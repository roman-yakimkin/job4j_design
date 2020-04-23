package ru.job4j.heroes;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class GameTest {
    @Test
    public void meleeBattleOneToOne() {
        UnitFactory unitFactory = new CustomUnitFactory();

//        unitFactory.addUnitPrototype("human_footman", "Пехотинец",
//                (name) -> (new CustomMeleeUnit(unitFactory, "human_footman", name, 100, List.of(
//                        new MeleeAction("Атака мечом", 18)
//                )))
//        );
//        unitFactory.addUnitPrototype("human_crossbowman", "Арбалетчик",
//                (name) -> (new CustomAmmoShootUnit(unitFactory, "human_crossbowman", name, 80, List.of(
//                        new AmmoShootAction("Выстрел из арбалета", 5),
//                        new MeleeAction("Атака клинком", 3)
//                ), 30))
//        );
//
//        unitFactory.addUnitPrototype("orc_goblin", "Гоблин",
//                (name) -> (new CustomMeleeUnit(unitFactory, "orc_goblin", name, 120, List.of(
//                        new MeleeAction("Удар дубиной", 20)
//                )))
//        );
//        unitFactory.addUnitPrototype("orc_bowman", "Орк-лучник",
//                (name) -> (new CustomAmmoShootUnit(unitFactory, "orc_bowman", name, 90, List.of(
//                        new AmmoShootAction("Выстрел из лука", 3),
//                        new MeleeAction("Атака клинком", 2)
//                ), 20))
//        );
//
//
//        Race humans = new CustomRace("Люди", RaceAttitude.POSITIVE, unitFactory);
//        humans.setDefaultArmyStructure(Map.of(
//                "human_footman", List.of("Footman 1", "Footman 2", "Footman 3", "Footman 4")
//                , "human_crossbowman", List.of("Crossbowman 1", "Crossbowman 2", "Crossbowman 3")
//        ));
//
//        Race orcs = new CustomRace("Орки", RaceAttitude.NEGATIVE, unitFactory);
//        orcs.setDefaultArmyStructure(Map.of(
//                "orc_goblin", List.of("Goblin 1", "Goblin 2", "Goblin 3", "Goblin 4")
//                ,"orc_bowman", List.of("Bowman 1", "Bowman 2", "Bowman 3")
//        ));
//
//        Player player1 = new CustomPlayer("Player 1", new BotDataInput());
//        Player player2 = new CustomPlayer("Player 2", new BotDataInput());
//
//        List<Unit> humanArmy = humans.createDefaultArmy();
//        player1.assignArmy(humanArmy);
//        List<Unit> orcArmy = orcs.createDefaultArmy();
//        player2.assignArmy(orcArmy);
//
//        BattleField battleField = new HeroesBattleField();
//        battleField.addArmy(humanArmy);
//        battleField.addArmy(orcArmy);


    }
}
