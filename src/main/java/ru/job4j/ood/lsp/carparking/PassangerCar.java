package ru.job4j.ood.lsp.carparking;

import java.util.Objects;

public class PassangerCar implements Car{
    public static final int CAR_SIZE = 1;
    private String carNumber;

    public PassangerCar(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassangerCar)) return false;
        PassangerCar that = (PassangerCar) o;
        return Objects.equals(getCarNumber(), that.getCarNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarNumber());
    }

    @Override
    public int getCarSize() {
        return CAR_SIZE;
    }
}
