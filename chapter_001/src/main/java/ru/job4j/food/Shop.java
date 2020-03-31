package ru.job4j.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shop implements IFoodStorage {

    private List<IFood> foodStorage;
    private IFoodStoragePermissions permissions;
    private Date currentDate;

    public Shop(IFoodStoragePermissions permissions, Date currentDate) {
        foodStorage = new ArrayList<>();
        this.permissions = permissions;
        this.currentDate = currentDate;
    }

    public IFoodStoragePermissions getPermissions() {
        return permissions;
    }

    public List<IFood> getFoodStorage() {
        return foodStorage;
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
        if (food.getStorageLifeInPercents(this.currentDate) >= 75.0) {
            food.setDiscount(this.calcDiscount(food));
        }
        return this.getFoodStorage().add(food);
    }

    public boolean removeFood(IFood food) {
        return this.getFoodStorage().remove(food);
    }
}
