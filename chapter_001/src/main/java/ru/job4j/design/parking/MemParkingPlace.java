package ru.job4j.design.parking;

import java.util.Optional;

/**
 * Класс - место на парковке
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.04.2020
 * @version 1.0
 */
public class MemParkingPlace implements ParkingPlace {
    private VehicleType type;
    private int number;
    private Optional<Vehicle> vehicle = Optional.empty();

    public MemParkingPlace(VehicleType type, int number) {
        this.type = type;
        this.number = number;
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public Optional<Vehicle> getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = Optional.of(vehicle);
    }

    @Override
    public void free() {
        this.vehicle = Optional.empty();
    }
}
