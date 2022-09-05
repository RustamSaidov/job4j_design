package ru.job4j.ood.lsp.carparking;

import java.util.Arrays;

import static java.util.Arrays.binarySearch;

public class PassangerCarParking implements Parking {
    private int[] doublePlaceForTruck = new int[2];

    private Car[] passangerParking;

    public PassangerCarParking(int sizeOfAParking) {
        this.passangerParking = new Car[sizeOfAParking];
    }

    @Override
    public boolean checkAndAddCar(Car car) {
        boolean result = false;
        if (car instanceof PassangerCar && !Arrays.asList(passangerParking).contains(((Truck) car).getCarNumber()) && Arrays.asList(passangerParking).contains(null)) {
            passangerParking[binarySearch(passangerParking, 0, passangerParking.length, null)] = car;
            result = true;
        }
        if (car instanceof Truck && !Arrays.asList(passangerParking).contains(((Truck) car).getCarNumber()) && isFreePlaceForATruck()) {
            passangerParking[doublePlaceForTruck[0]] = car;
            passangerParking[doublePlaceForTruck[1]] = car;
            result = true;
        }
        return result;
    }

    @Override
    public boolean freeUpParkingPlace(Car car) {
        return false;
    }

    private boolean isFreePlaceForATruck(){
        boolean result = false;
        Arrays.fill(doublePlaceForTruck, -1);
        for(int i=0; i<passangerParking.length; i++){
            if(passangerParking[i] == null && passangerParking[i+1] == null){
                doublePlaceForTruck[0] = i;
                doublePlaceForTruck[1] = i+1;
                result = true;
                break;
            }
        }
        return  result;
    }
}
