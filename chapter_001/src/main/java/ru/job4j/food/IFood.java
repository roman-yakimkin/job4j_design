package ru.job4j.food;

import java.util.Date;

/**
 * Интерфейс для продуктов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 30.03.2020
 * @version 1.0
 */
public interface IFood {
    public float getStorageLifeInPercents(Date aDate);
    public void setDiscount(byte discount);
    public byte getDiscount();
}
