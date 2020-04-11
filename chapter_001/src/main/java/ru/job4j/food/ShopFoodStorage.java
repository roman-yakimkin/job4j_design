package ru.job4j.food;

import java.util.Date;
import java.util.List;

/**
 * Класс - магазин
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 11.04.2020
 * @version 1.0
 */
public class ShopFoodStorage implements FoodStorage {
    private FoodStorage foodStorage;
    private Date currentDate;

    public ShopFoodStorage(FoodStorage foodStorage, Date currentDate) {
        this.foodStorage = foodStorage;
        this.currentDate = currentDate;
    }

    @Override
    public boolean shouldBeAdded(Food food, Date aDate) {
        return foodStorage.shouldBeAdded(food, aDate);
    }

    @Override
    public boolean shouldBeRemoved(Food food, Date aDate) {
        return foodStorage.shouldBeRemoved(food, aDate);
    }

    /**
     * Функция для расчета размера скидки в зависимости от продукта и даты расчета
     * @param food - продукт
     * @return размер скидки в процентах
     */
    private byte calcDiscount(Food food) {
        return (byte) 50;
    }

    @Override
    public void addFood(Food food) {
        if (food.getStorageLifeInPercents(currentDate) >= 75.0) {
            food.setDiscount(calcDiscount(food));
        }
        foodStorage.addFood(food);
    }

    @Override
    public boolean contains(Food food) {
        return foodStorage.contains(food);
    }

    @Override
    public List<Food> removeUnsuitableFood(Date aDate) {
        return foodStorage.removeUnsuitableFood(aDate);
    }

    @Override
    public List<Food> addSuitableFood(List<Food> addingFood, Date aDate) {
        return foodStorage.addSuitableFood(addingFood, aDate);
    }
}
