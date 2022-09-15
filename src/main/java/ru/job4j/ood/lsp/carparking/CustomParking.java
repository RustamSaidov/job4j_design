package ru.job4j.ood.lsp.carparking;

public class CustomParking implements Parking {
    private TruckParking truckParking;
    private PassangerCarParking passangerCarParking;

    public CustomParking(int sizeOftruckParking, int sizeOfPassangerCarParking) {
        this.truckParking = new TruckParking(sizeOftruckParking);
        this.passangerCarParking = new PassangerCarParking(sizeOfPassangerCarParking);
    }

    @Override
    public boolean add(Car car) {
        boolean result;
        if (car.getCarSize() > 0 && car.getCarSize() != PassangerCar.CAR_SIZE && truckParking.add(car)) {
            result = true;
        } else {
            result = passangerCarParking.add(car);
        }
        return result;
    }

    @Override
    public Car[] getCarArray() {
        return new Car[0];
    }

    public TruckParking getTruckParking() {
        return truckParking;
    }

    public PassangerCarParking getPassangerCarParking() {
        return passangerCarParking;
    }
}
