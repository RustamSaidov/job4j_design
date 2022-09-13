package ru.job4j.ood.lsp.carparking;

import java.util.Objects;

public class Truck implements Car {
    private final int carSize;
    private final String carNumber;

    public Truck(String carNumber, int carSize) {
        if (carSize <= PassangerCar.CAR_SIZE) {
            throw new IllegalArgumentException("Вы пытаетесь зарегистрировать грузовик размером с легковушку");
        } else {
            this.carNumber = carNumber;
            this.carSize = carSize;
        }
    }

    @Override
    public String getCarNumber() {
        return carNumber;
    }

    @Override
    public int getCarSize() {
        return carSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Truck)) {
            return false;
        }
        Truck truck = (Truck) o;
        return getCarSize() == truck.getCarSize() && Objects.equals(getCarNumber(), truck.getCarNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarSize(), getCarNumber());
    }
}
