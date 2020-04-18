package ru.job4j.design.parking;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Класс - парковка
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.04.2020
 * @version 1.0
 */
public class MemParking implements Parking {
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<ParkingPlace> places = new ArrayList<>();

    public MemParking(int carPlaces, int truckPlaces) {
        int placesNumerator = 1;
        for (int i = 0; i < carPlaces; i++) {
            places.add(new MemParkingPlace(VehicleType.CAR, placesNumerator++));
        }
        for (int i = 0; i < truckPlaces; i++) {
            places.add(new MemParkingPlace(VehicleType.TRUCK, placesNumerator++));
        }
    }

    /**
     * Найти последовательность незанятых парковочных мест для транспортных средств одного типа требуемой длины
     * @param vehicleType - типа транспортного средства
     * @param requiredLength - требуемая длина
     * @return - набор парковочных мест для парковки или пустой набор, если нет
     */
    public List<Integer> getMaxSequenceOfAvailablePlaces(VehicleType vehicleType, int requiredLength) {
        List<Integer> sequence = new ArrayList<>();
        List<ParkingPlace> srcPlaces = places
                .stream()
                .filter((place) -> (place.getType() == vehicleType))
                .collect(Collectors.toList());
        for (ParkingPlace place : srcPlaces) {
            if (place.getVehicle().isEmpty()) {
                sequence.add(place.getNumber());
                if (sequence.size() == requiredLength) {
                    return sequence;
                }
            } else {
                sequence.clear();
            }
        }
        return List.of();
    }

    /**
     * Процесс добавления транспортного средства на определенные мест парковки
     * @param vehicle - транспортное средство
     * @param parkingPlacesIndexes - места парковки
     */
    private void addVehicleToParkingPlaces(Vehicle vehicle, List<Integer> parkingPlacesIndexes) {
        places.stream()
                .filter((place) -> parkingPlacesIndexes.contains(place.getNumber()))
                .forEach((place) -> place.setVehicle(vehicle));
    }

    /**
     * Удаление транспортного средства с определенных мест парковки
     * @param vehicle - транспортное средство
     */
    private void removeVehicleFromParkingPlaces(Vehicle vehicle) {
        places.stream()
                .filter((place) -> place.getVehicle().equals(Optional.of(vehicle)))
                .forEach(ParkingPlace::free);
    }

    /**
     * Добавить транспортное средство на парковку
     * @param vehicle - транспортное средство
     * @return - истина, если средство добавлено
     */
    @Override
    public boolean addVehicle(Vehicle vehicle) {
        List<Integer> placeNumbers = new ArrayList<>();
        if (vehicle.getType() == VehicleType.CAR) {
            placeNumbers = getMaxSequenceOfAvailablePlaces(VehicleType.CAR, 1);
        } else if (vehicle.getType() == VehicleType.TRUCK) {
            placeNumbers = getMaxSequenceOfAvailablePlaces(VehicleType.TRUCK, 1);
            if (placeNumbers.isEmpty()) {
                placeNumbers = getMaxSequenceOfAvailablePlaces(VehicleType.CAR, vehicle.getSize());
            }
        }
        if (!placeNumbers.isEmpty()) {
            addVehicleToParkingPlaces(vehicle, placeNumbers);
            vehicles.add(vehicle);
        }
        return !placeNumbers.isEmpty();
    }

    /**
     * Удалить транспортное средство с парковки
     * @param vehicle - транспортное средство
     * @return - истина, если средство удалено
     */
    @Override
    public boolean removeVehicle(Vehicle vehicle) {
       places.stream()
            .filter((place) -> (place.getVehicle().equals(Optional.of(vehicle))))
            .forEach(ParkingPlace::free);
       return vehicles.remove(vehicle);
    }

    /**
     * Получить список транспортных средств по определенному критерию
     * @param condition - критерий
     * @return - список транспортных средств
     */
    @Override
    public List<Vehicle> getVehicles(Predicate<Vehicle> condition) {
        return vehicles.stream().filter(condition).collect(Collectors.toList());
    }

    /**
     * Список парковочных мест по определенному критерию
     * @param condition - критерий
     * @return - список парковочных мест
     */
    @Override
    public List<ParkingPlace> getPlaces(Predicate<ParkingPlace> condition) {
        return places.stream().filter(condition).collect(Collectors.toList());
    }

    /**
     * Список парковочных мест, занимаемых опереденным транспортным средством
     * @param vehicle - транспортное средство
     * @return = список парковочных мест
     */
    @Override
    public List<ParkingPlace> getPlacesForVehicle(Vehicle vehicle) {
        return getPlaces((place) -> (place.getVehicle().equals(Optional.of(vehicle))));
    }
}
