package ru.job4j.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Класс - хранилище продуктов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.03.2020
 * @version 1.0
 */
public class CommonFoodStorage implements FoodStorage {
    private List<Food> foodProducts = new ArrayList<>();
    private FoodStoragePermissions permissions;

    public CommonFoodStorage(FoodStoragePermissions permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean shouldBeAdded(Food food, Date aDate) {
        return permissions.shouldBeAdded(food, aDate);
    }

    @Override
    public boolean shouldBeRemoved(Food food, Date aDate) {
        return permissions.shouldBeRemoved(food, aDate);
    }

    @Override
    public void addFood(Food food) {
        foodProducts.add(food);
    }

    @Override
    public boolean contains(Food food) {
        return foodProducts.contains(food);
    }

    /**
     * Добавить в хранилище подходящую еду из списка
     * @param addingFood - список еды дял возможного добавления
     * @param aDate - дата, на которую рассчитывается годность еды
     * @return - список добавленной еды
     */
    @Override
    public List<Food> addSuitableFood(List<Food> addingFood, Date aDate) {
        List<Food> addedFood = new ArrayList<>();
        for (Food food : addingFood) {
            if (shouldBeAdded(food, aDate)) {
                addFood(food);
                addedFood.add(food);
            }
        }
        return addedFood;
    }

    /**
     * Убрать из хранилища еду, которая там не должна находиться
     * @param date - дата расчета
     * @return - удаленная из хранилища еда
     */
    @Override
    public List<Food> removeUnsuitableFood(Date date) {
        List<Food> unsuitableFood = new ArrayList<>();

        for (Food food : foodProducts) {
            if (shouldBeRemoved(food, date)) {
                unsuitableFood.add(food);
            }
        }
        foodProducts.removeAll(unsuitableFood);

        return unsuitableFood;
    }
}
