package ru.job4j.serialization.json;

import java.util.Arrays;

public class CinemaObject {
    private boolean isRealised;
    private short yearOfRealise;
    private String nameOfCinema;
    private CinemaCreatorInfo cinemaCreatorInfo;
    private int[] montlyIncome;

    public CinemaObject(boolean isRealised, short yearOfRealise, String nameOfCinema, CinemaCreatorInfo cinemaCreatorInfo, int[] montlyIncome) {
        this.isRealised = isRealised;
        this.yearOfRealise = yearOfRealise;
        this.nameOfCinema = nameOfCinema;
        this.cinemaCreatorInfo = cinemaCreatorInfo;
        this.montlyIncome = montlyIncome;
    }

    public boolean isRealised() {
        return isRealised;
    }

    public short getYearOfRealise() {
        return yearOfRealise;
    }

    public String getNameOfCinema() {
        return nameOfCinema;
    }

    public CinemaCreatorInfo getCinemaCreatorInfo() {
        return cinemaCreatorInfo;
    }

    public int[] getMontlyIncome() {
        return montlyIncome;
    }

    @Override
    public String toString() {
        return "CinemaObjectForXMLTask{"
                + "isRealised=" + isRealised
                + ", yearOfRealise=" + yearOfRealise
                + ", nameOfCinema='" + nameOfCinema + '\''
                + ", cinemaCreatorInfo=" + cinemaCreatorInfo
                + ", montlyIncome=" + Arrays.toString(montlyIncome)
                + '}';
    }
}
