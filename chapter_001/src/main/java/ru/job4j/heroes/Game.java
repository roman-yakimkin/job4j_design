package ru.job4j.heroes;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Класс - игра
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 19.04.2020
 * @version 1.0
 */
public class Game {
    private List<Player> players = new ArrayList<>();
    private BattleField battleField = new HeroesBattleField();
    private UnitFactory unitFactory;
    private List<Race> races = new ArrayList<>();
    private DataInput dataInput;
    private DataOutput dataOutput;
    private Player currentPlayer;

    private Map<Player, List<Unit>> unitsQueue = new HashMap<>();

    public Game(UnitFactory unitFactory, BattleField battleField, List<Player> players, List<Race> races, DataInput dataInput, DataOutput dataOutput) {
        this.unitFactory = unitFactory;
        this.battleField = battleField;
        this.players = players;
        this.races = races;
        this.dataInput = dataInput;
        this.dataOutput = dataOutput;
    }

    private Player getLoser() {
        for (Player player : players) {
            if (battleField.getFilteredUnits((u) -> (u.getPlayer().equals(player) && u.getHitPoints() > 0)).isEmpty()) {
                return player;
            }
        }
        return null;
    }

    private Player getWinner() {
        return getOpponent(getLoser());
    }

    private Player getOpponent(Player player) {
        return player != null ? players.stream().filter((p) -> (!p.equals(player))).collect(Collectors.toList()).get(0) : null;
    };

    private void initUnitsQueue() {
        for (Player player : players) {
            List<Unit> queue = battleField.getFilteredUnits((u) -> (u.getPlayer().equals(player) && u.getHitPoints() > 0));
            unitsQueue.put(player, queue);
        }
    }

    private void excludeUnitFromQueue(Player player, Unit unit) {
        List<Unit> queue = unitsQueue.get(player);
        queue.remove(unit);
        unitsQueue.put(player, queue);
    }

    private void removeKilledUnitsFromQueue(Player player) {
        List<Unit> queue = unitsQueue.get(player);
        queue = queue.stream().filter((u) -> (u.getHitPoints() > 0)).collect(Collectors.toList());
        unitsQueue.put(player, queue);
    }

    private boolean isNextRound() {
        boolean result = true;
        for (Player player : players) {
            result  = result && unitsQueue.get(player).isEmpty();
        }
        return result;
    }

    public void execute() {
        int tour = 1;
        Player winner = null;
        currentPlayer = players.get(0);
        initUnitsQueue();
        do {
            dataOutput.outputBattleField(battleField, players);
            Unit currentUnit = dataInput.getUnitForAction(currentPlayer, unitsQueue.get(currentPlayer));
            if (currentUnit != null) {
                dataOutput.outputMessage(System.lineSeparator() + "Ход № " + tour + ", cейчас ходит игрок " + currentPlayer.getName() + ", раса - " + currentPlayer.getRace().getName());
                UnitAction currentAction = dataInput.getUnitAction(battleField, currentUnit);
                List<Unit> targets = dataInput.getTargetsForAction(battleField, currentUnit, currentAction);
                Map<Unit, BiConsumer<Unit, Unit>> actionResults = currentAction.execute(targets);
                for (Map.Entry<Unit, BiConsumer<Unit, Unit>> entry : actionResults.entrySet()) {
                    Unit target = entry.getKey();
                    entry.getValue().accept(currentUnit, target);
                }
                dataOutput.outputAction(currentUnit, currentAction, targets);
                currentUnit.setUnitState(UnitState.NORMAL);
                excludeUnitFromQueue(currentPlayer, currentUnit);
                removeKilledUnitsFromQueue(getOpponent(currentPlayer));
            }
            currentPlayer = getOpponent(currentPlayer);
            tour++;
            if (isNextRound()) {
                initUnitsQueue();
            }
            winner = getWinner();
        } while (winner == null);
        dataOutput.outputMessage("Победил игрок " + winner.getName());
    }

    public static void main(String[] args) {
        UnitFactory unitFactory = new CustomUnitFactory();
        DataInput dataInput = new BotDataInput();
        DataOutput dataOutput = new ConsoleDataOutput();

        unitFactory.addUnitPrototype("human_footman", "Пехотинец",
                (name) -> (new CustomMeleeUnit(unitFactory, "human_footman", name, 100, List.of(
                        new MeleeAction("Атака мечом", 18)
                )))
        );
        unitFactory.addUnitPrototype("human_crossbowman", "Арбалетчик",
                (name) -> (new CustomAmmoShootUnit(unitFactory, "human_crossbowman", name, 80, List.of(
                    new AmmoShootAction("Выстрел из арбалета", 5),
                    new MeleeAction("Атака клинком", 3)
                ), 30))
        );
        unitFactory.addUnitPrototype("human_mage", "Маг",
                (name) -> (new CustomMageUnit(unitFactory, "human_mage", name, 60, List.of(
                        new ShootAction("Магический удар", 4),
                        new CustomMagicAction("Благословение", UnitState.BLESSED, Unit::getAllyUnits)
                )))
        );

        unitFactory.addUnitPrototype("elf_footman", "Эльф-пехотинец",
                (name) -> (new CustomMeleeUnit(unitFactory, "elf_footman", name, 100, List.of(
                        new MeleeAction("Атака мечом", 15)
                )))
        );
        unitFactory.addUnitPrototype("elf_bowman", "Эльфийский лучник",
                (name) -> (new CustomAmmoShootUnit(unitFactory, "elf_bowman", name, 80, List.of(
                        new AmmoShootAction("Выстрел из лука", 7),
                        new MeleeAction("Атака клинком", 3)
                ), 30))
        );
        unitFactory.addUnitPrototype("elf_sorcerer", "Эльф-маг",
                (name) -> (new CustomMageUnit(unitFactory, "elf_sorcerer", name, 60, List.of(
                        new ShootAction("Магический удар", 10),
                        new CustomMagicAction("Благословение", UnitState.BLESSED, Unit::getAllyUnits)
                )))
        );

        unitFactory.addUnitPrototype("orc_goblin", "Гоблин",
                (name) -> (new CustomMeleeUnit(unitFactory, "orc_goblin", name, 120, List.of(
                        new MeleeAction("Удар дубиной", 20)
                )))
        );
        unitFactory.addUnitPrototype("orc_bowman", "Орк-лучник",
                (name) -> (new CustomAmmoShootUnit(unitFactory, "orc_bowman", name, 90, List.of(
                        new AmmoShootAction("Выстрел из лука", 3),
                        new MeleeAction("Атака клинком", 2)
                ), 20))
        );
        unitFactory.addUnitPrototype("orc_shaman", "Оркский шаман",
                (name) -> (new CustomMageUnit(unitFactory, "orc_shaman", name, 60, List.of(
                        new CustomMagicAction("Проклятие", UnitState.NORMAL,
                                (unit) -> (unit.getOppositeUnits().stream()
                                        .filter((u) -> (u.getUnitState() == UnitState.BLESSED))
                                        .collect(Collectors.toList()))),
                        new CustomMagicAction("Благословение", UnitState.BLESSED, Unit::getAllyUnits)
                )))
        );

        unitFactory.addUnitPrototype("undead_zombie", "Зомби",
                (name) -> (new CustomMeleeUnit(unitFactory, "undead_zombie", name, 120, List.of(
                        new MeleeAction("Удар копьем", 18)
                )))
        );
        unitFactory.addUnitPrototype("undead_hunter", "Нежить-охотник",
                (name) -> (new CustomAmmoShootUnit(unitFactory, "undead_hunter", name, 90, List.of(
                        new AmmoShootAction("Выстрел из лука", 4),
                        new MeleeAction("Атака клинком", 2)
                ), 40))
        );
        unitFactory.addUnitPrototype("undead_necromancer", "Некромант",
                (name) -> (new CustomMageUnit(unitFactory, "undead_necromancer", name, 60, List.of(
                        new ShootAction("Атака", 5),
                        new CustomMagicAction("Наслать недуг", UnitState.DAMNED, Unit::getOppositeUnits)
                )))
        );

        Race humans = new CustomRace("Люди", RaceAttitude.POSITIVE, unitFactory);
        humans.setDefaultArmyStructure(Map.of(
                "human_crossbowman", List.of("Crossbowman 1", "Crossbowman 2", "Crossbowman 3")
                ,"human_footman", List.of("Footman 1", "Footman 2", "Footman 3", "Footman 4")
                , "human_mage", List.of("Mage 1")
        ));

        Race elves = new CustomRace("Эльфы", RaceAttitude.POSITIVE, unitFactory);
        elves.setDefaultArmyStructure(Map.of(
                "elf_footman", List.of("Footman 1", "Footman 2", "Footman 3", "Footman 4")
                , "elf_bowman", List.of("Bowman 1", "Bowman 2", "Bowman 3")
                , "elf_sorcerer", List.of("Sorcerer 1")
        ));

        Race orcs = new CustomRace("Орки", RaceAttitude.NEGATIVE, unitFactory);
        orcs.setDefaultArmyStructure(Map.of(
                "orc_goblin", List.of("Goblin 1", "Goblin 2", "Goblin 3", "Goblin 4")
                ,"orc_bowman", List.of("Bowman 1", "Bowman 2", "Bowman 3")
                ,"orc_shaman", List.of("Shaman 1")
        ));

        Race undead = new CustomRace("Нежить", RaceAttitude.NEGATIVE, unitFactory);
        undead.setDefaultArmyStructure(Map.of(
                "undead_zombie", List.of("Zombie 1", "Zombie 2", "Zombie 3", "Zombie 4")
                , "undead_hunter", List.of("Hunter 1", "Hunter 2", "Hunter 3")
                , "undead_necromancer", List.of("Necromancer 1")
        ));

        List<Race> races = List.of(humans, orcs, elves, undead);
        Random r = new Random();
        Race race1 = races.get(r.nextInt(races.size()));
        List<Race> oppositeRaces = races.stream().filter((rc) -> (rc.getAttitude() != race1.getAttitude())).collect(Collectors.toList());
        Race race2 = oppositeRaces.get(r.nextInt(oppositeRaces.size()));

        Player player1 = new CustomPlayer("Player 1", race1, dataInput);
        Player player2 = new CustomPlayer("Player 2", race2, dataInput);

        List<Unit> army1 = race1.createDefaultArmy();
        player1.assignArmy(army1);
        List<Unit> army2 = race2.createDefaultArmy();
        player2.assignArmy(army2);

        BattleField battleField = new HeroesBattleField();
        battleField.addArmy(army1);
        battleField.addArmy(army2);

        Game game = new Game(unitFactory, battleField, List.of(player1, player2), List.of(race1, race2), dataInput, dataOutput);
        game.execute();

    }
}
