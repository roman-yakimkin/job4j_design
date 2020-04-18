package ru.job4j.design.parking;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс - парковка
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 31.03.2020
 * @version 1.0
 */
public interface Parking {
    public boolean addVehicle(Vehicle vehicle);
    public boolean removeVehicle(Vehicle vehicle);
    public List<Vehicle> getVehicles(Predicate<Vehicle> filter);
    public List<ParkingPlace> getPlaces(Predicate<ParkingPlace> filter);
    public List<ParkingPlace> getPlacesForVehicle(Vehicle vehicle);
}
