package ru.job4j.food;

import java.util.Date;

public class TrashPermissions implements FoodStoragePermissions {
    @Override
    public boolean shouldBeAdded(Food food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) >= 100.0);
    }

    @Override
    public boolean shouldBeRemoved(Food food, Date aDate) {
        return false;
    }
}
