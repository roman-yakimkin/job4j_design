package ru.job4j.design.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - легковое транспортное средство
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.04.2020
 * @version 1.0
 */
public class Car implements Vehicle {
    private String model;
    private String plateNumber;

    public Car(String model, String plateNumber) {
        this.model = model;
        this.plateNumber = plateNumber;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getPlateNumber() {
        return plateNumber;
    }

    @Override
    public int getSize() {
        return 1;
    }
}
