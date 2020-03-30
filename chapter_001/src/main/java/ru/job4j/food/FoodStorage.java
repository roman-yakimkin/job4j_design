package ru.job4j.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс - хранилище продуктов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.03.2020
 * @version 1.0
 */
abstract public class FoodStorage implements IFoodStorage {
    private List<IFood> foodStorage;

    public FoodStorage() {
        foodStorage = new ArrayList<>();
    }

    public List<IFood> getFoodStorage() {
        return foodStorage;
    }

    public void addFood(IFood food) {
        this.foodStorage.add(food);
    }

    public void removeFood(IFood food) {
        this.foodStorage.remove(food);
    }

    abstract public boolean shouldBeAdded(IFood food, Date aDate);
    abstract public boolean shouldBeRemoved(IFood food, Date aDate);
}
