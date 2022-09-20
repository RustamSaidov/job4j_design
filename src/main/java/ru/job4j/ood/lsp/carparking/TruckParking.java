package ru.job4j.ood.lsp.carparking;

import java.util.HashSet;
import java.util.Set;

public class TruckParking implements Parking {

    private Car[] truckParking;
    private int sizeOfAParking;
    private int numberOfCarsInParking;
    private final Set<Car> setOfCars = new HashSet<>();

    public TruckParking(int sizeOfAParking) {
        this.sizeOfAParking = sizeOfAParking;
        this.truckParking = new Car[this.sizeOfAParking];
        numberOfCarsInParking = 0;
    }

    @Override
    public boolean add(Car car) {
        if (setOfCars.contains(car)) {
            return false;
        }
        if (car.getCarSize() <= PassangerCar.CAR_SIZE || sizeOfAParking == numberOfCarsInParking) {
            return false;
        }
        setOfCars.add(car);
        truckParking[numberOfCarsInParking++] = car;
        return true;
    }

    @Override
    public Car[] getCarArray() {
        return truckParking.clone();
    }
}
