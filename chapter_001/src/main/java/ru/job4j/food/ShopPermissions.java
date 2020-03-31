package ru.job4j.food;

import java.util.Date;

public class ShopPermissions implements IFoodStoragePermissions {
    public boolean shouldBeAdded(IFood food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) >= 25.0 && food.getStorageLifeInPercents(aDate) < 100.0) ;
    }

    public boolean shouldBeRemoved(IFood food, Date aDate) {
        return ((food.getStorageLifeInPercents(aDate) < 25.0) || ((food.getStorageLifeInPercents(aDate) >= 100.0)));
    }
}
