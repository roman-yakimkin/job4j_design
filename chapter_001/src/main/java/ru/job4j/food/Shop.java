package ru.job4j.food;

import java.util.Date;

public class Shop extends FoodStorage {

    private Date currentDate;

    public Shop(Date currentDay) {
        super();
        this.currentDate = currentDay;
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

    public boolean shouldBeAdded(IFood food, Date aDate) {
        return (food.getStorageLifeInPercents(aDate) >= 25.0 && food.getStorageLifeInPercents(aDate) < 100.0) ;
    }

    public boolean shouldBeRemoved(IFood food, Date aDate) {
        return ((food.getStorageLifeInPercents(aDate) < 25.0) || ((food.getStorageLifeInPercents(aDate) >= 100.0)));
    }

    @Override
    public void addFood(IFood food) {
        if (food.getStorageLifeInPercents(this.currentDate) >= 75.0) {
            food.setDiscount(this.calcDiscount(food));
        }
        super.addFood(food);
    }
}
