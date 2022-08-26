package ru.job4j.ood.srp;

import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("[").append(System.lineSeparator());
        List<Employee> list = store.findBy(filter);
        int i = 0;
        for (Employee employee : list) {
            text.append(new GsonBuilder().create().toJson(employee).toString())
                    .append(i < list.size() - 1 ? "," : "").append(System.lineSeparator());
            i++;
        }
        text.append("]").append(System.lineSeparator());

        return text.toString();
    }
}
