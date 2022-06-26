package ru.job4j.serialization.json;

public class CinemaCreatorInfo {
    String directorName;
    String directorSurname;
    String producerName;
    String producerSurname;

    public CinemaCreatorInfo(String directorName, String directorSurname, String producerName, String producerSurname) {
        this.directorName = directorName;
        this.directorSurname = directorSurname;
        this.producerName = producerName;
        this.producerSurname = producerSurname;
    }

    @Override
    public String toString() {
        return "CinemaCreatorInfo{"
                + "directorName='" + directorName + '\''
                + ", directorSurname='" + directorSurname + '\''
                + ", producerName='" + producerName + '\''
                + ", producerSurname='" + producerSurname + '\''
                + '}';
    }
}
