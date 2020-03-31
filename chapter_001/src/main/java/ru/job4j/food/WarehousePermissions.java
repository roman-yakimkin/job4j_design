package ru.job4j.food;

import java.util.Date;

public class WarehousePermissions implements IFoodStoragePermissions {
    @Override
    public boolean shouldBeAdded(IFood food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) < 25.0);
    }

    @Override
    public boolean shouldBeRemoved(IFood food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) >= 25.0);
    }
}
