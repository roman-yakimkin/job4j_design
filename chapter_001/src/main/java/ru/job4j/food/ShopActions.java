package ru.job4j.food;

import java.util.Date;

public class ShopActions implements IFoodStorageActions {
    IFoodStorage storage;
    private Date currentDate;

    public ShopActions(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public IFoodStorage getStorage() {
        return this.storage;
    }

    @Override
    public void setStorage(IFoodStorage storage) {
        this.storage = storage;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * Функция для расчета размера скидки в зависимости от продукта и даты расчета
     * @param food - продукт
     * @return размер скидки в процентах
     */
    private byte calcDiscount(IFood food) {
        return (byte) 50;
    }

    @Override
    public boolean addFood(IFood food) {
        if (food.getStorageLifeInPercents(currentDate) >= 75.0) {
            food.setDiscount(calcDiscount(food));
        }
        return getStorage().getFoodStorage().add(food);
    }

    @Override
    public boolean removeFood(IFood food) {
        return getStorage().getFoodStorage().remove(food);
    }

}
