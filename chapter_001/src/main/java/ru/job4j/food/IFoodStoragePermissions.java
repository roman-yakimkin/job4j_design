package ru.job4j.food;

import java.util.Date;

public interface IFoodStoragePermissions {
    public boolean shouldBeAdded(IFood food, Date aDate);
    public boolean shouldBeRemoved(IFood food, Date aDate);
}
