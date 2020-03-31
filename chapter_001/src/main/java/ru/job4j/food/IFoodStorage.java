package ru.job4j.food;

import java.util.Date;
import java.util.List;

public interface IFoodStorage {
    public boolean addFood(IFood food);
    public boolean removeFood(IFood food);
    public List<IFood> getFoodStorage();
    public IFoodStoragePermissions getPermissions();
}
