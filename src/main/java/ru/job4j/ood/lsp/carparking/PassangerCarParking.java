package ru.job4j.ood.lsp.carparking;

import java.util.*;

public class PassangerCarParking implements Parking {
    private int[] dimentionOfPlaceForTruck;
    private int sizeOfAParking;
    private int numberOfCarsInParking;

    private Car[] passangerParking;

    public PassangerCarParking(int sizeOfAParking) {
        this.sizeOfAParking = sizeOfAParking;
        this.passangerParking = new Car[this.sizeOfAParking];
        numberOfCarsInParking = 0;
    }

    @Override
    public boolean checkAndAddCar(Car car) {
        boolean result = false;
        Set<Car> setOfCars = new HashSet<>(Arrays.asList(passangerParking));
        if (car.getCarSize() == 1 && !setOfCars.contains(car) && numberOfCarsInParking < sizeOfAParking) {
            passangerParking[Arrays.asList(passangerParking).indexOf(null)] = car;
            numberOfCarsInParking = numberOfCarsInParking + car.getCarSize();
            result = true;
        }
        if (car.getCarSize() > 1 && !setOfCars.contains(car) && isFreePlaceForATruck(car, passangerParking)) {
            getPlacesForTruck(dimentionOfPlaceForTruck[0]);
            for (int i = 0; i < dimentionOfPlaceForTruck.length; i++) {
                passangerParking[dimentionOfPlaceForTruck[i]] = car;
            }
            numberOfCarsInParking = numberOfCarsInParking + car.getCarSize();
            result = true;
        }
        return result;
    }
//    }

    public Car[] getCarArray() {
        return passangerParking.clone();
    }

    @Override
    public boolean freeUpParkingPlace(Car car) {
        return false;
    }

    public boolean isFreePlaceForATruck(Car car, Car[] passangerParking) {
        boolean result = true;
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
        return result;
    }

    private void getPlacesForTruck(int beginningOfATruckPlace) {
        int j = beginningOfATruckPlace;
        for (int k = 0; k < dimentionOfPlaceForTruck.length; k++) {
            dimentionOfPlaceForTruck[k] = j;
            j = j + 1;
        }
    }
}
