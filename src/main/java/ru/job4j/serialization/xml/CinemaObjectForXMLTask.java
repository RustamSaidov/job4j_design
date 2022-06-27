package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.CinemaCreatorInfo;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "cinemaObjectForXMLTask")
@XmlAccessorType(XmlAccessType.FIELD)
public class CinemaObjectForXMLTask {
    @XmlAttribute
    private boolean isRealised;
    @XmlAttribute
    private short yearOfRealise;
    @XmlAttribute
    private String nameOfCinema;
    private CinemaCreatorInfoForXMLTask cinemaCreatorInfoForXMLTask;

    @XmlElementWrapper(name = "montlyIncomes")
    @XmlElement(name = "montlyIncome")
    private int[] montlyIncome;

    public CinemaObjectForXMLTask() {
    }

    public CinemaObjectForXMLTask(boolean isRealised, short yearOfRealise, String nameOfCinema, CinemaCreatorInfoForXMLTask cinemaCreatorInfoForXMLTask, int[] montlyIncome) {
        this.isRealised = isRealised;
        this.yearOfRealise = yearOfRealise;
        this.nameOfCinema = nameOfCinema;
        this.cinemaCreatorInfoForXMLTask = cinemaCreatorInfoForXMLTask;
        this.montlyIncome = montlyIncome;
    }

    @Override
    public String toString() {
        return "CinemaObjectForXMLTask{"
                + "isRealised=" + isRealised
                + ", yearOfRealise=" + yearOfRealise
                + ", nameOfCinema='" + nameOfCinema + '\''
                + ", cinemaCreatorInfo=" + cinemaCreatorInfoForXMLTask
                + ", montlyIncome=" + Arrays.toString(montlyIncome)
                + '}';
    }
}
