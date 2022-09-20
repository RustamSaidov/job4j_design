package ru.job4j.ood.lsp.carparking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomParkingTest {

    @Test
    public void whenPassCarGoesToPassCarParking() {
        CustomParking customParking = new CustomParking(2, 5);
        Car[] passArray = new Car[5];
        Car car11 = new PassangerCar("X111XX");
        customParking.add(car11);
        passArray[0] = car11;
        assertArrayEquals(passArray, customParking.getPassangerCarParking().getCarArray());
    }

    @Test
    public void whenPassCarGoesToTruckParking() {
        CustomParking customParking = new CustomParking(2, 5);
        Car[] truckArray = new Car[2];
        Car car11 = new PassangerCar("X111XX");
        customParking.getTruckParking().add(car11);
        assertArrayEquals(truckArray, customParking.getTruckParking().getCarArray());
    }

    @Test
    public void whenTruckGoesToTruckParking() {
        CustomParking customParking = new CustomParking(2, 5);
        Car[] truckArray = new Car[2];
        Car car1 = new Truck("Y111YY", 2);
        customParking.add(car1);
        truckArray[0] = car1;
        assertArrayEquals(truckArray, customParking.getTruckParking().getCarArray());
    }

    @Test
    public void whenTruckGoesToPassCarParking() {
        CustomParking customParking = new CustomParking(2, 5);
        Car[] passArray = new Car[5];
        Car car1 = new Truck("Y111YY", 2);
        customParking.getPassangerCarParking().add(car1);
        passArray[0] = car1;
        passArray[1] = car1;
        assertArrayEquals(passArray, customParking.getPassangerCarParking().getCarArray());
    }

    @Test
    public void whenPassCarGoesTwiceToPassCarParking() {
        CustomParking customParking = new CustomParking(2, 5);
        Car[] passArray = new Car[5];
        Car car11 = new PassangerCar("X111XX");
        customParking.add(car11);
        customParking.add(car11);
        passArray[0] = car11;
        assertArrayEquals(passArray, customParking.getPassangerCarParking().getCarArray());
    }

    @Test
    public void whenTruckGoesTwiceToPassCarParking() {
        CustomParking customParking = new CustomParking(2, 5);
        Car[] passArray = new Car[5];
        Car car1 = new Truck("Y111YY", 2);
        customParking.getPassangerCarParking().add(car1);
        customParking.getPassangerCarParking().add(car1);
        passArray[0] = car1;
        passArray[1] = car1;
        assertArrayEquals(passArray, customParking.getPassangerCarParking().getCarArray());
    }

    @Test
    public void whenPassCarGoesToFilledPassCarParking() {
        CustomParking customParking = new CustomParking(2, 2);
        Car[] passArray = new Car[2];
        Car car11 = new PassangerCar("X111XX");
        Car car22 = new PassangerCar("X222XX");
        Car car55 = new PassangerCar("X555XX");
        customParking.add(car11);
        customParking.add(car22);
        customParking.add(car55);
        passArray[0] = car11;
        passArray[1] = car22;
        assertArrayEquals(passArray, customParking.getPassangerCarParking().getCarArray());
    }

    @Test
    public void whenTruckGoesTofilledTruckParking() {
        CustomParking customParking = new CustomParking(2, 1);
        Car[] truckArray = new Car[2];
        Car[] truckInPassParkArray = new Car[1];
        Car car1 = new Truck("Y111YY", 2);
        Car car2 = new Truck("Y222YY", 2);
        Car car3 = new Truck("Y333YY", 2);
        customParking.add(car1);
        customParking.add(car2);
        customParking.add(car2);
        truckArray[0] = car1;
        truckArray[1] = car2;
        assertArrayEquals(truckArray, customParking.getTruckParking().getCarArray());
        assertArrayEquals(truckInPassParkArray, customParking.getPassangerCarParking().getCarArray());
    }


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