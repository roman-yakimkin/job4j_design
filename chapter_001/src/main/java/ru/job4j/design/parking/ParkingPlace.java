package ru.job4j.design.parking;

import java.util.Optional;

/**
 * Интерфейс - место на парковочной площадке
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 31.03.2020
 */
public interface ParkingPlace {
    public VehicleType getType();
    public int getNumber();
    public Optional<Vehicle> getVehicle();
    public void setVehicle(Vehicle vehicle);
    public void free();
}
