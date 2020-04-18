package ru.job4j.design.parking;

/**
 * Фабрика классов для парковки
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 18.04.2020
 * @version 1.0
 */
public class MemParkingFactory implements ParkingFactory {

    /**
     * Создать парковочное место
     * @param type - тип места
     * @param number - номер места
     * @return - парковочное место
     */
    @Override
    public ParkingPlace createParkingPlace(VehicleType type, int number) {
        return new MemParkingPlace(type, number);
    }
}
