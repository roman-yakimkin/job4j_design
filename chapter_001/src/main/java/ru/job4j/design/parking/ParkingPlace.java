package ru.job4j.design.parking;

/**
 * Интерфейс - место на парковочной площадке
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 31.03.2020
 */
public interface ParkingPlace {
    public int getType();
    public int getNumber();
    public Vehicle getVehicle();
}
