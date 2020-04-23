package ru.job4j.design.parking;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * Класс - менеджер парковки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 18.04.2020
 * @version 1.0
 */
public class MemParkingManager implements ParkingManager {
    /**
     * Ссылка на класс - парковку
     */
    private Parking parking;
    /**
     * Карта транспортных средств с ключом по номеру квитанции
     */
    private Map<String, Vehicle> vehicles = new HashMap<>();

    public MemParkingManager(Parking parking){
        this.parking = parking;
    }

    /**
     * Добавить транспортное средство на парковку
     * @param vehicle - транспортное средство
     * @return - номер квитанции
     */
    @Override
    public Optional<String> put(Vehicle vehicle) {
        Optional<String> voucher = Optional.empty();
        if (parking.addVehicle(vehicle)) {
            Random r = new Random();
            voucher = Optional.of(vehicle.getPlateNumber() + "-" + r.nextInt(1000000));
            vehicles.put(voucher.get(), vehicle);
        }
        return voucher;
    }

    /**
     * Получить транспортное средство по номеру квитанцци
     * @param voucher - номер квитанции
     * @return - транспортное средство
     */
    @Override
    public Optional<Vehicle> get(String voucher) {
        Optional<Vehicle> vehicle = Optional.of(vehicles.get(voucher));
        vehicle.ifPresent(value -> parking.removeVehicle(value));
        return vehicle;
    }
}
