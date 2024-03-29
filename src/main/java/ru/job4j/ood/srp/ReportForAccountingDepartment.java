package ru.job4j.ood.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportForAccountingDepartment implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public static final double YEAR_SALARY_COEFF = 12 / 0.87;

    private Store store;

    public ReportForAccountingDepartment(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(String.format("%.2f", employee.getSalary() * YEAR_SALARY_COEFF)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
