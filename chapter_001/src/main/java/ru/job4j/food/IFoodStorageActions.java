package ru.job4j.food;

public interface IFoodStorageActions {
    public IFoodStorage getStorage();
    public void setStorage(IFoodStorage storage);
    public boolean addFood(IFood food);
    public boolean removeFood(IFood food);
}
