package ru.job4j.food;

import java.util.Date;

public class ShopPermissions implements FoodStoragePermissions {
    public boolean shouldBeAdded(Food food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) >= 25.0 && food.getStorageLifeInPercents(aDate) < 100.0) ;
    }

    public boolean shouldBeRemoved(Food food, Date aDate) {
        return ((food.getStorageLifeInPercents(aDate) < 25.0) || ((food.getStorageLifeInPercents(aDate) >= 100.0)));
    }
}
