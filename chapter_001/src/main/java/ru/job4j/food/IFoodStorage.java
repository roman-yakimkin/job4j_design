package ru.job4j.food;

import java.util.Date;
import java.util.List;

public interface IFoodStorage {
    public List<IFood> getFoodStorage();
    public IFoodStoragePermissions getPermissions();
    public IFoodStorageActions getActions();
}
