package ru.job4j.ood.lsp.carparking;

public class CustomParking implements Parking{
    private TruckParking truckParking;
    private PassangerCarParking passangerCarParking;

    public CustomParking(int SizeOftruckParking, int SizeOfPassangerCarParking) {
        this.truckParking = new TruckParking(SizeOftruckParking);
        this.passangerCarParking = new PassangerCarParking(SizeOfPassangerCarParking);
    }

    @Override
    public boolean checkAndAddCar(Car car) {
        boolean result = false;
        if(truckParking.checkAndAddCar(car)) {
            result = true;
        } else{
            result = passangerCarParking.checkAndAddCar(car);
        }
        return result;
    }

    @Override
    public boolean freeUpParkingPlace(Car car) {
        return false;
    }
}
