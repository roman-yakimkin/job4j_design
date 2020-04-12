package ru.job4j.design.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    @Test
    public void calculateParkingPlacesIfOneTruck() {
        Parking parking = new MemParking(3, 1);
        Vehicle kamaz5320 = new Truck("КамАЗ-5320", "AA 1234", 3);
        parking.addVehicle(kamaz5320);
        int freePlacesForCars = parking.getPlaces((place) -> (place.getVehicle() == null && place.getType() == 1)).size();
        int freePlacesForTrucks = parking.getPlaces((place) -> (place.getVehicle() == null && place.getType() == 2)).size();
        assertThat(freePlacesForCars, is(3));
        assertThat(freePlacesForTrucks, is(0));
    }

    @Test
    public void calculateParkingPlacesIfTwoCarsAndOneTruck() {
        Parking parking = new MemParking(3, 1);
        Vehicle kamaz5320 = new Truck("КамАЗ-5320", "AA 1234", 3);
        Vehicle zaz965 = new Car("ЗАЗ 965", "БО 2345");
        Vehicle zaz968 = new Car("ЗАЗ 968", "АВ 3467");
        parking.addVehicle(kamaz5320);
        parking.addVehicle(zaz965);
        parking.addVehicle(zaz968);
        int freePlacesForCars = parking.getPlaces((place) -> (place.getVehicle() == null && place.getType() == 1)).size();
        int freePlacesForTrucks = parking.getPlaces((place) -> (place.getVehicle() == null && place.getType() == 2)).size();
        assertThat(freePlacesForCars, is(1));
        assertThat(freePlacesForTrucks, is(0));
    }
}
