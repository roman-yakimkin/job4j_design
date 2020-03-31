package ru.job4j.food;

public class DefaultFoodStorageActions implements IFoodStorageActions {

    IFoodStorage storage;

    public DefaultFoodStorageActions() {
    }

    @Override
    public IFoodStorage getStorage() {
        return this.storage;
    }

    @Override
    public void setStorage(IFoodStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean addFood(IFood food) {
        return this.getStorage().getFoodStorage().add(food);
    }

    @Override
    public boolean removeFood(IFood food) {
        return this.getStorage().getFoodStorage().remove(food);
    }
}
