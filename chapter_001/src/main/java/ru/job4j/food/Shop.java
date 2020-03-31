package ru.job4j.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shop implements IFoodStorage {

    private List<IFood> foodStorage;
    private IFoodStoragePermissions permissions;
    private Date currentDate;

    public Shop(IFoodStoragePermissions permissions, Date currentDate) {
        foodStorage = new ArrayList<>();
        this.permissions = permissions;
        this.currentDate = currentDate;
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
}
