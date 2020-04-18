package ru.job4j.design.parking;

/**
 * Интерфейс для фабрики классов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 18.04.2020
 * @version 1.0
 */
public interface ParkingFactory {
    public ParkingPlace createParkingPlace(VehicleType type, int number);
}
