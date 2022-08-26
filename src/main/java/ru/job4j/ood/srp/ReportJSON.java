package ru.job4j.ood.srp;

import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;
    GsonBuilder gson;

    public ReportJSON(Store store) {
        this.store = store;
        this.gson = new GsonBuilder();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();

        var employees = store.findBy(filter);
        text.append(gson.create().toJson(employees));

        return text.toString();
    }
}
