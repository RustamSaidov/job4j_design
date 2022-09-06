package ru.job4j.ood.lsp.carparking;

public class CustomParking implements Parking {
    private TruckParking truckParking;
    private PassangerCarParking passangerCarParking;

    public CustomParking(int sizeOftruckParking, int sizeOfPassangerCarParking) {
        this.truckParking = new TruckParking(sizeOftruckParking);
        this.passangerCarParking = new PassangerCarParking(sizeOfPassangerCarParking);
    }

    @Override
    public boolean checkAndAddCar(Car car) {
        boolean result;
        if (truckParking.checkAndAddCar(car)) {
            result = true;
        } else {
            result = passangerCarParking.checkAndAddCar(car);
        }
        return result;
    }

    @Override
    public boolean freeUpParkingPlace(Car car) {
        return false;
    }

    public TruckParking getTruckParking() {
        return truckParking;
    }

    public PassangerCarParking getPassangerCarParking() {
        return passangerCarParking;
    }
}
