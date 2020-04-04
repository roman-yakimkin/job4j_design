package ru.job4j.food;

import java.util.Date;
import java.util.List;

public interface IFoodStorage {
    public List<IFood> getFoodStorage();
    public boolean shouldBeAdded(IFood food, Date aDate);
    public boolean shouldBeRemoved(IFood food, Date aDate);
    public void addFood(IFood food);
    public boolean contains(IFood food);
}
