package ru.job4j.food;

import java.util.Date;

/**
 * Класс - продукт питания
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.03.2020
 * @version 1.0
 */
public class ExpirableFood implements Food {
    /**
     * Наименование продукта
     */
    private String name;
    /**
     * Дата производства продукта
     */
    private Date createDate;
    /**
     * Дата истечения срока годности
     */
    private Date expireDate;
    /**
     * Стоимость продукта
     */
    private int price;
    /**
     * Процент скидки
     */
    private byte discount = 0;

    public ExpirableFood(String name, Date createDate, Date expireDate, int price) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getDiscount() {
        return discount;
    }

    public void setDiscount(byte discount) {
        this.discount = discount;
    }

    public int getCalculatedPrice() {
        return (int) (price * (100 - discount) / 100);
    }

    /**
     * Расчет срока годности продукта в зависимости от даты
     * @param aDate - дата расчета
     * @return - значение срока годности в процентах
     */
    public float getStorageLifeInPercents(Date aDate) {
        return (float) ((aDate.getTime() - createDate.getTime()) * 100 / (expireDate.getTime() - createDate.getTime()));
    }
}
