package ru.job4j.food;

import java.util.Date;

public class TrashPermissions implements IFoodStoragePermissions {
    @Override
    public boolean shouldBeAdded(IFood food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) >= 100.0);
    }

    @Override
    public boolean shouldBeRemoved(IFood food, Date aDate) {
        return false;
    }
}
