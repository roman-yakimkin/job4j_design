package ru.job4j.design.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Поставщик координат для бота
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 13.04.2020
 * @version 1.0
 */
public class BotDataInput implements DataInput {
    /**
     * Хранилище координат
     */
    private List<Coords> coords = new ArrayList<>();
    /**
     *  Индекс для хранилища координат
     */
    private int coordsIndex = 0;
    /**
     *  Ссылка на игрока
     */
    private Player player;
    /**
     * Ссылка на интерфейс вывода данных
     */
    private DataOutput dataOutput;

    public BotDataInput(String coordsSequence) {
        this.dataOutput = dataOutput;
        int x, y;
        for (String coordsDelimitedWithSpace : coordsSequence.split(",")) {
            String[] oneCoordsPair = coordsDelimitedWithSpace.trim().split("\\s");
            try {
                x = Integer.parseInt(oneCoordsPair[0]);
                y = Integer.parseInt(oneCoordsPair[1]);
                coords.add(new MemCoords(x, y));
            } catch (Exception e) {
                this.dataOutput.outputMessage("Вы ввели некорректные значения. Пожалуйста, введите их заново");
            }
        }
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void setDataOutput(DataOutput dataOutput) {
        this.dataOutput = dataOutput;
    }

    @Override
    public Coords getCoords() {
        Coords result = null;
        if (coordsIndex < coords.size()) {
            result = new MemCoords(coords.get(coordsIndex++));
            dataOutput.outputMessage(player.getName() + " выбирает координаты (" + result.getX() + ", " + result.getY() + ") для " + player.getSymbol());
        }
        return result;
    }
}
