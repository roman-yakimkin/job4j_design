package ru.job4j.food;

import java.util.Date;

public interface FoodStoragePermissions {
    public boolean shouldBeAdded(Food food, Date aDate);
    public boolean shouldBeRemoved(Food food, Date aDate);
}
