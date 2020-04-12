package ru.job4j.design.parking;

/**
 * Интерфейс - менеджер парковки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.04.2020
 * @version 1.0
 */
public interface ParkingManager {
    public String put(Vehicle vehicle);
    public Vehicle get(String voucher);
}
