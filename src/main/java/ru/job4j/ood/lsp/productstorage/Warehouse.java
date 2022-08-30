package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private static Warehouse instance;
    private static List<Food> FOOD_LIST;

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
            FOOD_LIST = new ArrayList<>();
        }
        return instance;
    }


    public static List<Food> getFoodList() {
        return FOOD_LIST;
    }

    @Override
    public void checkToAdd(Food food, double foodSheltLifePers) {
        if (foodSheltLifePers > 0.75) {
            FOOD_LIST.add(food);
        }
    }
}
