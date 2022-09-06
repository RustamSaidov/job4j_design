package ru.job4j.ood.lsp.carparking;

import ru.job4j.ood.lsp.productstorage.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.binarySearch;

public class PassangerCarParking implements Parking {
    private int[] dimentionOfPlaceForTruck;

    private Car[] passangerParking;

    public PassangerCarParking(int sizeOfAParking) {
        this.passangerParking = new Car[sizeOfAParking];
    }

    @Override
    public boolean checkAndAddCar(Car car) {
        boolean result = false;
        if (car instanceof PassangerCar && !Arrays.asList(passangerParking).contains(car) && Arrays.asList(passangerParking).contains(null)) {
            passangerParking[Arrays.asList(passangerParking).indexOf(null)] = car;
            result = true;
        }
        if (car instanceof Truck && !Arrays.asList(passangerParking).contains(car) && isFreePlaceForATruck(car, passangerParking)) {
            getPlacesForTruck(dimentionOfPlaceForTruck[0]);
            for (int i = 0; i < dimentionOfPlaceForTruck.length; i++) {
                passangerParking[dimentionOfPlaceForTruck[i]] = car;
            }
            result = true;
        }
        return result;
    }

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

        for (int i = 0; i < passangerParking.length; i++) {
            result = true;
            if (i + car.getCarSize() < passangerParking.length + 1) {
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
