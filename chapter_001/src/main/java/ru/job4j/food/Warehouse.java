package ru.job4j.food;

import java.util.Date;

/**
 * Класс - склад товаров
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.03.2020
 * @version 1.0
 */
public class Warehouse extends FoodStorage {

    @Override
    public boolean shouldBeAdded(IFood food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) < 25.0);
    }

    @Override
    public boolean shouldBeRemoved(IFood food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) >= 25.0);
    }
}
