package ru.job4j.design.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingManagerTest {

    @Test
    public void ifVehiclePutItShouldBeGet() {
        Parking parking = new MemParking(10, 10);
        Vehicle receivedCar = null;
        ParkingManager parkingManager = new MemParkingManager(parking);
        Vehicle azlk2141 = new Car("АЗЛК 2141", "AA 1234");
        String voucher = parkingManager.put(azlk2141);
        if (voucher != null) {
            receivedCar = parkingManager.get(voucher);
        }
        assertThat(receivedCar, is(azlk2141));
    }

    @Test
    public void ifVehicleIsTruckAndThereArePlacesOnlyForCars() {
        Parking parking = new MemParking(10, 0);
        int parkingPlaces = 0;
        ParkingManager parkingManager = new MemParkingManager(parking);
        Vehicle belaz540A = new Truck("БелАЗ-540А", "XX 2345", 5);
        String voucher = parkingManager.put(belaz540A);
        if (voucher != null) {
            parkingPlaces = belaz540A.getParkingPlaces().size();
        }
        assertThat(parkingPlaces, is(5));
    }

    @Test
    public void ifVehicleIsTruckAndThereAreNotEnoughParkingPlaces() {
        Parking parking = new MemParking(3, 1);
        ParkingManager parkingManager = new MemParkingManager(parking);
        Vehicle kamaz5320 = new Truck("КамАЗ-5320", "AA 1234", 3);
        String voucher1 = parkingManager.put(kamaz5320);
        Vehicle belaz540A = new Truck("БелАЗ-540А", "XX 2345", 5);
        String voucher2 = parkingManager.put(belaz540A);
        assertNotNull(voucher1);
        assertNull(voucher2);
    }
}
