package ru.job4j.ood.lsp.carparking;

public interface Parking {

    boolean checkAndAddCar(Car car);

    boolean freeUpParkingPlace(Car car);
}
