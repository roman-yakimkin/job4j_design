package ru.job4j.food;

import java.util.Date;
import java.util.List;

public interface IFoodStorage {
    public boolean shouldBeAdded(IFood food, Date aDate);
    public boolean shouldBeRemoved(IFood food, Date aDate);
    public void addFood(IFood food);
    public void removeFood(IFood food);
    public List<IFood> getFoodStorage();
}
