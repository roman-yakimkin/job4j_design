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
public class FoodStorage implements IFoodStorage {
    private List<IFood> foodStorage;
    private IFoodStoragePermissions permissions;
    private IFoodStorageActions actions;

    public FoodStorage(IFoodStoragePermissions permissions, IFoodStorageActions actions) {
        this.foodStorage = new ArrayList<>();
        this.permissions = permissions;
        this.actions = actions;
        this.actions.setStorage(this);
    }

    public List<IFood> getFoodStorage() {
        return foodStorage;
    }

    @Override
    public IFoodStoragePermissions getPermissions() {
        return permissions;
    }

    @Override
    public IFoodStorageActions getActions() {
        return actions;
    }
}
