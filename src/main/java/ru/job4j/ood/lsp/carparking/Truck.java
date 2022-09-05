package ru.job4j.ood.lsp.carparking;

import java.util.Objects;

public class Truck implements Car{
    private static final int CAR_SIZE = 2;
    private String carNumber;

    public Truck(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck)) return false;
        Truck truck = (Truck) o;
        return Objects.equals(getCarNumber(), truck.getCarNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarNumber());
    }
}
