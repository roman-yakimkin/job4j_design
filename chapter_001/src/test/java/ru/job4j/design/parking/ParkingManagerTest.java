package ru.job4j.design.parking;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingManagerTest {

    @Test
    public void ifVehiclePutItShouldBeGet() {
        Parking parking = new MemParking(new MemParkingFactory(), 10, 10);
        Optional<Vehicle> receivedCar = null;
        ParkingManager parkingManager = new MemParkingManager(parking);
        Vehicle azlk2141 = new Car("АЗЛК 2141", "AA 1234");
        var voucher = parkingManager.put(azlk2141);
        if (voucher.isPresent()) {
            receivedCar = parkingManager.get(voucher.get());
        }
        assertThat(receivedCar.get(), is(azlk2141));
    }

    @Test
    public void ifVehicleIsTruckAndThereArePlacesOnlyForCars() {
        Parking parking = new MemParking(new MemParkingFactory(), 10, 0);
        int parkingPlacesCount = 0;
        ParkingManager parkingManager = new MemParkingManager(parking);
        Vehicle belaz540A = new Truck("БелАЗ-540А", "XX 2345", 5);
        Optional<String> voucher = parkingManager.put(belaz540A);
        if (voucher.isPresent()) {
            parkingPlacesCount = parking.getPlacesForVehicle(belaz540A).size();
        }
        assertThat(parkingPlacesCount, is(5));
    }

    @Test
    public void ifVehicleIsTruckAndThereAreNotEnoughParkingPlaces() {
        Parking parking = new MemParking(new MemParkingFactory(), 3, 1);
        ParkingManager parkingManager = new MemParkingManager(parking);
        Vehicle kamaz5320 = new Truck("КамАЗ-5320", "AA 1234", 3);
        Optional<String> voucher1 = parkingManager.put(kamaz5320);
        Vehicle belaz540A = new Truck("БелАЗ-540А", "XX 2345", 5);
        Optional<String> voucher2 = parkingManager.put(belaz540A);
        assertThat(voucher1.isPresent(), is(true));
        assertThat(voucher2.isEmpty(), is(true));
    }

    @Test
    public void ifWePutAndTookAllTheVehiclesTheParkingShouldBeEmpty() {
        Parking parking = new MemParking(new MemParkingFactory(), 3, 1);
        ParkingManager parkingManager = new MemParkingManager(parking);
        Vehicle kamaz5320 = new Truck("КамАЗ-5320", "AA 1234", 3);
        Optional<String> voucher1 = parkingManager.put(kamaz5320);
        Vehicle azlk2141 = new Car("АЗЛК 2141", "AA 1234");
        Optional<String> voucher2 = parkingManager.put(azlk2141);
        parkingManager.get(voucher1.get());
        parkingManager.get(voucher2.get());
        assertThat(parking.getPlaces((place) -> (place.getType() == VehicleType.CAR)).size(), is(3));
        assertThat(parking.getPlaces((place) -> (place.getType() == VehicleType.TRUCK)).size(), is(1));
    }
}
