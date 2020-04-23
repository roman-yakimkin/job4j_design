package ru.job4j.design.parking;

import java.util.List;

/**
 * Интерфейс - транспортное средство
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 31.03.2020
 * @version 1.0
 */
public interface Vehicle {
    public VehicleType getType();
    public String getModel();
    public String getPlateNumber();
    public int getSize();
}
