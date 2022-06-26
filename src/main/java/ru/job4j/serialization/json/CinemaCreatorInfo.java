package ru.job4j.serialization.json;

public class CinemaCreatorInfo {
    String directorName;
    String getDirectorSurname;
    String producerName;
    String producerSurname;

    public CinemaCreatorInfo(String directorName, String getDirectorSurname, String producerName, String producerSurname) {
        this.directorName = directorName;
        this.getDirectorSurname = getDirectorSurname;
        this.producerName = producerName;
        this.producerSurname = producerSurname;
    }

    @Override
    public String toString() {
        return "CinemaCreatorInfo{"
                + "directorName='" + directorName + '\''
                + ", getDirectorSurname='" + getDirectorSurname + '\''
                + ", producerName='" + producerName + '\''
                + ", producerSurname='" + producerSurname + '\''
                + '}';
    }
}
