package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class CinemaObjectMainForXMLTask {
    public static void main(String[] args) throws Exception {
        final CinemaObjectForXMLTask cinemaObjectForXMLTask = new CinemaObjectForXMLTask(true, (short) 1984, "Terminator",
                new CinemaCreatorInfoForXMLTask("James", "Cameron", "Gale", "Anne Hurd"), new int[]{500000, 400000, 300000});
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(CinemaObjectForXMLTask.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(cinemaObjectForXMLTask, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            CinemaObjectForXMLTask result = (CinemaObjectForXMLTask) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
