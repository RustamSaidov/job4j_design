package ru.job4j.ood.lsp.carparking;

import java.util.Arrays;

public class CustomParking implements Parking {
    private TruckParking truckParking;
    private PassangerCarParking passangerCarParking;

    public CustomParking(int sizeOftruckParking, int sizeOfPassangerCarParking) {
        this.truckParking = new TruckParking(sizeOftruckParking);
        this.passangerCarParking = new PassangerCarParking(sizeOfPassangerCarParking);
    }

    @Override
    public boolean add(Car car) {
        return truckParking.add(car) || passangerCarParking.add(car);
    }

    @Override
    public Car[] getCarArray() {
        Car[] arraySource1 = passangerCarParking.getCarArray();
        Car[] arraySource2 = truckParking.getCarArray();
        Car[] mergedArray = new Car[arraySource1.length + arraySource2.length];
        System.arraycopy(arraySource1, 0, mergedArray, 0, arraySource1.length);
        System.arraycopy(arraySource2, 0, mergedArray, arraySource1.length, arraySource2.length);
        return mergedArray;
    }

    public TruckParking getTruckParking() {
        return truckParking;
    }

    public PassangerCarParking getPassangerCarParking() {
        return passangerCarParking;
    }
}
