package ru.job4j.ood.lsp.productStorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Food food1 = new Food("chips", new GregorianCalendar(2025, Calendar.JANUARY, 30).getTime(),
                new GregorianCalendar(2022, Calendar.MARCH, 30).getTime(), 100, 0.50);
        Food food2 = new Food("chocolade", new GregorianCalendar(2023, Calendar.JANUARY, 30).getTime(),
                new GregorianCalendar(2022, Calendar.MARCH, 30).getTime(), 100, 0.50);
        Food food3 = new Food("milk", new GregorianCalendar(2022, Calendar.SEPTEMBER, 30).getTime(),
                new GregorianCalendar(2022, Calendar.MARCH, 30).getTime(), 100, 0.50);
        Food food4 = new Food("fish", new GregorianCalendar(2021, Calendar.JANUARY, 30).getTime(),
                new GregorianCalendar(2020, Calendar.MARCH, 30).getTime(), 100, 0.50);
        ControlQuality controlQuality = new ControlQuality();
        List<Store> stores = new ArrayList<>();
        stores.add(Trash.getInstance());
        stores.add(Shop.getInstance());
        stores.add(Warehouse.getInstance());

        controlQuality.distribute(food1,stores);
        controlQuality.distribute(food2,stores);
        controlQuality.distribute(food3,stores);
        controlQuality.distribute(food4,stores);
        System.out.println("Trash " + Trash.getFoodList());
        System.out.println("Shop " + Shop.getFoodList());
        System.out.println("Warehouse " + Warehouse.getFoodList());

    }
}
