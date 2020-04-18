package ru.job4j.design.parking;

import java.util.Optional;

/**
 * Интерфейс - менеджер парковки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.04.2020
 * @version 1.0
 */
public interface ParkingManager {
    public Optional<String> put(Vehicle vehicle);
    public Optional<Vehicle> get(String voucher);
}
