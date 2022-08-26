package ru.job4j.ood.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportXML implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        StringBuilder text = new StringBuilder();
        var employees = store.findBy(filter);

            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xml = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employee(employees), writer);
                xml = writer.getBuffer().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            text.append(xml);

        return text.toString();
    }
}
