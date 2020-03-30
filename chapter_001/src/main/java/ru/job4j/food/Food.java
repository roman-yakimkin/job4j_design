package ru.job4j.food;

import java.util.Date;

/**
 * Класс - продукт питания
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.03.2020
 * @version 1.0
 */
public class Food implements IFood {
    private String name;
    private Date createDate;
    private Date expireDate;
    private int price;
    private byte discount;

    public Food(String name, Date createDate, Date expireDate, int price) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
