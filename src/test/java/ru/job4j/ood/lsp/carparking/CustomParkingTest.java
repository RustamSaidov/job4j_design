package ru.job4j.ood.lsp.carparking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomParkingTest {

    @Test
    public void whenTruckParkingIsFilledAndTruckGoesToPassParkingWhichIsPartlyFilledAndTheAnotherOnePassCarFilledPassParking() {
        CustomParking customParking = new CustomParking(2, 5);
        Car[] truckArray = new Car[2];
        Car[] truckInPassParkArray = new Car[5];
        Car car1 = new Truck("Y111YY", 2);
        Car car2 = new Truck("Y222YY", 2);
        Car car3 = new Truck("Y333YY", 2);
        Car car4 = new Truck("Y333YY", 44);
        Car car11 = new PassangerCar("X111XX");
        Car car22 = new PassangerCar("X222XX");
        Car car55 = new PassangerCar("X555XX");
        Car car66 = new PassangerCar("X666XX");
        assertTrue(customParking.add(car1));
        assertTrue(customParking.add(car2));
        assertTrue(customParking.add(car11));
        assertTrue(customParking.add(car22));
        assertTrue(customParking.add(car3));
        assertFalse(customParking.add(car4));
        assertTrue(customParking.add(car55));
        assertFalse(customParking.add(car66));
        truckArray[0] = car1;
        truckArray[1] = car2;
        truckInPassParkArray[0] = car11;
        truckInPassParkArray[1] = car22;
        truckInPassParkArray[2] = car3;
        truckInPassParkArray[3] = car3;
        truckInPassParkArray[4] = car55;
        assertArrayEquals(truckArray, customParking.getTruckParking().getCarArray());
        assertArrayEquals(truckInPassParkArray, customParking.getPassangerCarParking().getCarArray());
    }
}