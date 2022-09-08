package ru.job4j.ood.lsp.carparking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TruckParking implements Parking {

    private Car[] truckParking;
    private int sizeOfAParking;
    private int numberOfCarsInParking;

    public TruckParking(int sizeOfAParking) {
        this.sizeOfAParking = sizeOfAParking;
        this.truckParking = new Car[this.sizeOfAParking];
        numberOfCarsInParking = 0;
    }

    @Override
    public boolean checkAndAddCar(Car car) {
        boolean result = false;
        Set<Car> setOfCars = new HashSet<>(Arrays.asList(truckParking));
        if (car.getCarSize() > 1 && !setOfCars.contains(car) && numberOfCarsInParking < sizeOfAParking) {
            truckParking[Arrays.asList(truckParking).indexOf(null)] = car;
            numberOfCarsInParking++;
            result = true;
        }
        return result;
    }

    public Car[] getCarArray() {
        return truckParking.clone();
    }

    @Override
    public boolean freeUpParkingPlace(Car car) {
        return false;
    }
}
