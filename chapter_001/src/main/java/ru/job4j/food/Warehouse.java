package ru.job4j.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс - склад товаров
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.03.2020
 * @version 1.0
 */
public class Warehouse implements IFoodStorage {

    private List<IFood> foodStorage;
    private IFoodStoragePermissions permissions;

    public Warehouse(IFoodStoragePermissions permissions) {
        foodStorage = new ArrayList<>();
        this.permissions = permissions;
    }

    public IFoodStoragePermissions getPermissions() {
        return permissions;
    }

    @Override
    public IFoodStorageActions getActions() {
        return null;
    }

    public List<IFood> getFoodStorage() {
        return foodStorage;
    }

    public boolean addFood(IFood food) {
        return this.getFoodStorage().add(food);
    }
    public boolean removeFood(IFood food) {
        return this.getFoodStorage().remove(food);
    }
}
