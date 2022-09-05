package ru.job4j.ood.lsp.carparking;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomParkingTest {

    @Test
    public void whenParkingIsEmpty() {
        PassangerCarParking passangerCarParking = new PassangerCarParking(5);
        TruckParking truckParking = new TruckParking(10);
        Car[] passAarray = new Car[5];
        Car[] truckArray = new Car[10];
        assertArrayEquals(passAarray, passangerCarParking.getCarArray());
        assertArrayEquals(truckArray, truckParking.getCarArray());
    }

    @Test
    public void whenAddOnePassCarToPassParking() {
        PassangerCarParking passangerCarParking = new PassangerCarParking(5);
        Car[] passAarray = new Car[5];
        Car car = new PassangerCar("X111XX");
        passangerCarParking.checkAndAddCar(car);
        passAarray[0] = car;

        assertArrayEquals(passAarray, passangerCarParking.getCarArray());

    }

    @Test
    public void whenFilledPassCarToPassParking() {
        PassangerCarParking passangerCarParking = new PassangerCarParking(5);
        Car[] passAarray = new Car[5];
        Car car1 = new PassangerCar("X111XX");
        Car car2 = new PassangerCar("X222XX");
        Car car3 = new PassangerCar("X333XX");
        Car car4 = new PassangerCar("X444XX");
        Car car5 = new PassangerCar("X555XX");
        passangerCarParking.checkAndAddCar(car1);
        passangerCarParking.checkAndAddCar(car2);
        passangerCarParking.checkAndAddCar(car3);
        passangerCarParking.checkAndAddCar(car4);
        passangerCarParking.checkAndAddCar(car5);
        passAarray[0] = car1;
        passAarray[1] = car2;
        passAarray[2] = car3;
        passAarray[3] = car4;
        passAarray[4] = car5;

        assertArrayEquals(passAarray, passangerCarParking.getCarArray());

    }

    @Test
    public void whenAddOneTruckToTruckParking() {
        TruckParking truckParking = new TruckParking(10);
        Car[] truckArray = new Car[10];
        Car car = new Truck("Y111YY", 2);
        truckParking.checkAndAddCar(car);
        truckArray[0] = car;
        assertArrayEquals(truckArray, truckParking.getCarArray());
    }

    @Test
    public void whenFilledTruckToTruckParking() {
        TruckParking truckParking = new TruckParking(5);
        Car[] truckArray = new Car[5];
        Car car1 = new Truck("Y111YY", 2);
        Car car2 = new Truck("Y222YY", 2);
        Car car3 = new Truck("Y333YY", 2);
        Car car4 = new Truck("Y444YY", 2);
        Car car5 = new Truck("Y555YY", 2);
        truckParking.checkAndAddCar(car1);
        truckParking.checkAndAddCar(car2);
        truckParking.checkAndAddCar(car3);
        truckParking.checkAndAddCar(car4);
        truckParking.checkAndAddCar(car5);
        truckArray[0] = car1;
        truckArray[1] = car2;
        truckArray[2] = car3;
        truckArray[3] = car4;
        truckArray[4] = car5;
        assertArrayEquals(truckArray, truckParking.getCarArray());
    }

    @Test
    public void whenTruckParkingIsFilledAndTruckGoesToPassParking() {
        CustomParking customParking = new CustomParking(2, 5);
        Car[] truckArray = new Car[2];
        Car[] truckInPassParkArray = new Car[5];
        Car car1 = new Truck("Y111YY", 2);
        Car car2 = new Truck("Y222YY", 2);
        Car car3 = new Truck("Y333YY", 2);
        customParking.checkAndAddCar(car1);
        customParking.checkAndAddCar(car2);
        customParking.checkAndAddCar(car3);
        truckArray[0] = car1;
        truckArray[1] = car2;
        truckInPassParkArray[0] = car3;
        truckInPassParkArray[1] = car3;
        assertArrayEquals(truckArray, customParking.getTruckParking().getCarArray());
        assertArrayEquals(truckInPassParkArray, customParking.getPassangerCarParking().getCarArray());
    }
}