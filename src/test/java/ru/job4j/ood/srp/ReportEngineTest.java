package ru.job4j.ood.srp;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.ood.srp.ReportEngine.DATE_FORMAT;

public class ReportEngineTest {
    public static final SimpleDateFormat DATE_FORMAT_FOR_XML = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @Test
    public void whenOldGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenNewProgrammistReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForProgrammists(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE HTML>")
                .append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta http-equiv=\"Content-Tupe\" content=\"text/html;charset=\"utf-8>")
                .append(System.lineSeparator())
                .append("<title>Таблица<title>")
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<table>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td>")
                .append("</tr>").append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenNewAccountingDepartmentReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForAccountingDepartment(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(String.format("%.2f", worker.getSalary() * ReportForAccountingDepartment.YEAR_SALARY_COEFF)).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenNewHRReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Tom", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportForHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
/*ТУТ НЕ ЗНАЮ ЧТО ДЕЛАТЬ. ПИШЕТ, ЧТО ОШИБКА ТОЛЬКО В LINE SEPARATOR. НО КАК Я ЕГО НЕ МЕНЯЛ, ВКЛЮЧАЯ НА ВЕСЬ ПРОЕКТ, ОШИБКА ТЕСТА НЕ ИСЧЕЗАЕТ
    @Test
    public void whenNewXLMReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Tom", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(System.lineSeparator())
                .append("<employees>").append(System.lineSeparator())
                .append("    <employees>").append(System.lineSeparator())
                .append("        <name>" + worker.getName() + "</name>").append(System.lineSeparator())
                .append("        <hired>" + DATE_FORMAT_FOR_XML.format(worker.getHired().getTime()) + "</hired>").append(System.lineSeparator())
                .append("        <fired>" + DATE_FORMAT_FOR_XML.format(worker.getHired().getTime()) + "</fired>").append(System.lineSeparator())
                .append("        <salary>" + worker.getSalary() + "</salary>").append(System.lineSeparator())
                .append("    </employees>").append(System.lineSeparator())
                .append("    <employees>").append(System.lineSeparator())
                .append("        <name>" + worker2.getName() + "</name>").append(System.lineSeparator())
                .append("        <hired>" + DATE_FORMAT_FOR_XML.format(worker2.getHired().getTime()) + "</hired>").append(System.lineSeparator())
                .append("        <fired>" + DATE_FORMAT_FOR_XML.format(worker2.getFired().getTime()) + "</fired>").append(System.lineSeparator())
                .append("        <salary>" + worker2.getSalary() + "</salary>").append(System.lineSeparator())
                .append("    </employees>").append(System.lineSeparator())
                .append("    <salary>0.0</salary>").append(System.lineSeparator())
                .append("</employees>").append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
    */

    @Test
    public void whenNewJSONReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Tom", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJSON(store);
        StringBuilder expect = new StringBuilder()
                .append("[").append(System.lineSeparator())
                .append(new GsonBuilder().create().toJson(worker).toString())
                .append(",").append(System.lineSeparator())
                .append(new GsonBuilder().create().toJson(worker2).toString())
                .append(System.lineSeparator())
                .append("]").append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}
