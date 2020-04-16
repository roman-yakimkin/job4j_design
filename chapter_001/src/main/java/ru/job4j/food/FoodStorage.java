package ru.job4j.food;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс - хранилище продуктов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 11.04.2020
 * @version 1.0
 */
public interface FoodStorage {
    public boolean shouldBeAdded(Food food, Date aDate);
    public boolean shouldBeRemoved(Food food, Date aDate);
    public void addFood(Food food);
    public boolean contains(Food food);
    public List<Food> removeUnsuitableFood(Date aDate);
    public List<Food> addSuitableFood(List<Food> addingFood, Date aDate);
}
