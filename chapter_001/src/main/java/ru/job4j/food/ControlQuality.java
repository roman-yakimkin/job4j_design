package ru.job4j.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Класс - контроль качества продуктов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 30.03.2020
 */
public class ControlQuality {
    private Date aDate;
    private List<FoodStorage> storages = new ArrayList<>();

    public ControlQuality(Date aDate) {
        this.aDate = aDate;
    }
    public void addStorage(FoodStorage storage){
        storages.add(storage);
    }

    /**
     * Пересортировка продуктов
     */
    public void rearrangeFood() {

        List<Food> temporary = new ArrayList<>();
        for (FoodStorage storage : storages) {
            temporary.addAll(storage.removeUnsuitableFood(aDate));
        }
        for (FoodStorage storage : storages) {
            temporary.removeAll(storage.addSuitableFood(temporary, aDate));
        }
    }

    /**
     * Перераспределение всех продуктов
     */
    public void resort() {
        List<Food> temporary = new ArrayList<>();
        for (FoodStorage storage : storages) {
            temporary.addAll(storage.takeFood((food) -> true));
        }
        for (FoodStorage storage : storages) {
            temporary.removeAll(storage.addSuitableFood(temporary, aDate));
        }
    }
}
