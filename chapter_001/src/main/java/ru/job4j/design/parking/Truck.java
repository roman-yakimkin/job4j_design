package ru.job4j.design.parking;

import java.util.List;

/**
 * Класс - грузовое транспортное средство
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.04.2020
 * @version 1.0
 */
public class Truck implements Vehicle {
    private Vehicle carInfo;
    private int size;

    public Truck(String model, String plateNumber, int size) {
        carInfo = new Car(model, plateNumber);
        this.size = size;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.TRUCK;
    }

    @Override
    public String getModel() {
        return carInfo.getModel();
    }

    @Override
    public String getPlateNumber() {
        return carInfo.getPlateNumber();
    }

    @Override
    public int getSize() {
        return size;
    }
}
