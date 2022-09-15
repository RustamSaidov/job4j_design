package ru.job4j.ood.lsp.carparking;

import java.util.*;

public class PassangerCarParking implements Parking {
    private int[] dimentionOfPlaceForTruck;
    private int sizeOfAParking;
    private int numberOfCarsInParking;

    private Car[] passangerParking;
    private final Set<Car> setOfCars = new HashSet<>();

    public PassangerCarParking(int sizeOfAParking) {
        this.sizeOfAParking = sizeOfAParking;
        this.passangerParking = new Car[this.sizeOfAParking];
        numberOfCarsInParking = 0;
    }

    @Override
    public boolean add(Car car) {
        if (car.getCarSize() == PassangerCar.CAR_SIZE && !setOfCars.contains(car) && numberOfCarsInParking < sizeOfAParking) {
            setOfCars.add(car);
            passangerParking[numberOfCarsInParking++] = car;
            return true;
        }
        if (car.getCarSize() < PassangerCar.CAR_SIZE || !isFreePlaceForATruck(car, passangerParking)) {
            return false;
        }
        int tempInt = numberOfCarsInParking;
        for (int i = tempInt; i < tempInt + car.getCarSize(); i++) {
            passangerParking[numberOfCarsInParking++] = car;
        }
        setOfCars.add(car);
        return true;
    }

    @Override
    public Car[] getCarArray() {
        return passangerParking.clone();
    }

    public boolean isFreePlaceForATruck(Car car, Car[] passangerParking) {
        return numberOfCarsInParking + car.getCarSize() < passangerParking.length;
        /*boolean result = true;
        dimentionOfPlaceForTruck = new int[car.getCarSize()];
        Arrays.fill(dimentionOfPlaceForTruck, -1);

        for (int i = 0; i < sizeOfAParking; i++) {
            result = true;
            if (i + car.getCarSize() < sizeOfAParking + 1) {
                for (int j = 0; j < car.getCarSize(); j++) {
                    if (!(passangerParking[j + i] == null)) {
                        result = false;
                    }
                }
            } else {
                result = false;
            }
            if (result) {
                dimentionOfPlaceForTruck[0] = i;
                break;
            }
        }
        return result;*/
    }
}
