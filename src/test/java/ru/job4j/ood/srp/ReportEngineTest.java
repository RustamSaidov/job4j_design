package ru.job4j.ood.srp;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static ru.job4j.ood.srp.ReportEngine.DATE_FORMAT;

public class ReportEngineTest {
    public static final SimpleDateFormat DATE_FORMAT_FOR_XML = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @Test
    public void whenOldGenerated() {
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
    public void whenNewProgrammistReportGenerated() {
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
    public void whenNewAccountingDepartmentReportGenerated() {
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
    public void whenNewHRReportGenerated() {
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

    @Test
    public void whenNewXLMReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Tom", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportXML(store);
        String result = engine.generate(employee -> true);
        StringJoiner expect = new StringJoiner("\n");
        expect.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .add("<employees>")
                .add("    <employees>")
                .add(String.format("        <fired>%s</fired>", DATE_FORMAT_FOR_XML.format(worker.getFired().getTime())))
                .add(String.format("        <hired>%s</hired>", DATE_FORMAT_FOR_XML.format(worker.getHired().getTime())))
                .add(String.format("        <name>%s</name>", worker.getName()))
                .add(String.format("        <salary>%s</salary>", worker.getSalary()))
                .add("    </employees>")
                .add("    <employees>")
                .add(String.format("        <fired>%s</fired>", DATE_FORMAT_FOR_XML.format(worker2.getFired().getTime())))
                .add(String.format("        <hired>%s</hired>", DATE_FORMAT_FOR_XML.format(worker2.getHired().getTime())))
                .add(String.format("        <name>%s</name>", worker2.getName()))
                .add(String.format("        <salary>%s</salary>", worker2.getSalary()))
                .add("    </employees>")
                .add("</employees>\n");
        assertEquals(result, expect.toString());
    }


    @Test
    public void whenNewJSONReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Tom", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJSON(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"").append(worker.getName()).append("\",\"hired\":{\"year\":").append(worker.getHired().get(Calendar.YEAR))
                .append(",\"month\":").append(worker.getHired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(worker.getHired().get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(worker.getHired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(worker.getHired().get(Calendar.MINUTE))
                .append(",\"second\":").append(worker.getHired().get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(worker.getFired().get(Calendar.YEAR))
                .append(",\"month\":").append(worker.getFired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(worker.getFired().get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(worker.getFired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(worker.getFired().get(Calendar.MINUTE))
                .append(",\"second\":").append(worker.getFired().get(Calendar.SECOND))
                .append("},\"salary\":").append(worker.getSalary()).append("},")
                .append("{\"name\":\"").append(worker2.getName()).append("\",\"hired\":{\"year\":").append(worker2.getHired().get(Calendar.YEAR))
                .append(",\"month\":").append(worker2.getHired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(worker2.getHired().get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(worker2.getHired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(worker2.getHired().get(Calendar.MINUTE))
                .append(",\"second\":").append(worker2.getHired().get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(worker2.getFired().get(Calendar.YEAR))
                .append(",\"month\":").append(worker2.getFired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(worker2.getFired().get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(worker2.getFired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(worker2.getFired().get(Calendar.MINUTE))
                .append(",\"second\":").append(worker2.getFired().get(Calendar.SECOND))
                .append("},\"salary\":").append(worker2.getSalary()).append("}]");


        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}
