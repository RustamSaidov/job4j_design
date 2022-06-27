package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cinemaCreatorInfoForXMLTask")
public class CinemaCreatorInfoForXMLTask {
    @XmlAttribute
    String directorName;
    @XmlAttribute
    String directorSurname;
    @XmlAttribute
    String producerName;
    @XmlAttribute
    String producerSurname;

    public CinemaCreatorInfoForXMLTask() {

    }

    public CinemaCreatorInfoForXMLTask(String directorName, String directorSurname, String producerName, String producerSurname) {
        this.directorName = directorName;
        this.directorSurname = directorSurname;
        this.producerName = producerName;
        this.producerSurname = producerSurname;
    }

    @Override
    public String toString() {
        return "CinemaCreatorInfoForXMLTask{"
                + "directorName='" + directorName + '\''
                + ", directorSurname='" + directorSurname + '\''
                + ", producerName='" + producerName + '\''
                + ", producerSurname='" + producerSurname + '\''
                + '}';
    }
}
