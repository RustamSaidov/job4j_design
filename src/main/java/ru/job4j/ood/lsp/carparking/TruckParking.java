package ru.job4j.ood.lsp.carparking;

import java.util.Arrays;

import static java.util.Arrays.binarySearch;

public class TruckParking implements Parking {

    private Car[] truckParking;

    public TruckParking(int sizeOfAParking) {
        this.truckParking = new Car[sizeOfAParking];
    }

    @Override
    public boolean checkAndAddCar(Car car) {
        boolean result = false;
        if (car instanceof Truck && !Arrays.asList(truckParking).contains(((Truck) car).getCarNumber()) && Arrays.asList(truckParking).contains(null)) {
            truckParking[binarySearch(truckParking, 0, truckParking.length, null)] = car;
            result = true;
        }
        return result;
    }

    @Override
    public boolean freeUpParkingPlace(Car car) {
        return false;
    }
}
