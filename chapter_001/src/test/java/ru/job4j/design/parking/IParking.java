package ru.job4j.design.parking;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс - парковка
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 31.03.2020
 * @version 1.0
 */
public interface IParking {
    public boolean addVehicle(IVehicle vehicle);
    public boolean removeVehicle(IVehicle vehicle);
    public List<IVehicle> getVehicles(Predicate<IVehicle> filter);
    public List<IParkingPlace> getPlaces(Predicate<IParkingPlace> filter);
}
