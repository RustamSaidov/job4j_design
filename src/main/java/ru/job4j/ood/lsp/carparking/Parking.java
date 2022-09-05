package ru.job4j.ood.lsp.carparking;

import java.util.List;

public interface Parking {

    boolean checkAndAddCar(Car car);

    boolean freeUpParkingPlace(Car car);
}
