package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private static Warehouse instance;
    private static List<Food> foodList;

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
            foodList = new ArrayList<>();
        }
        return instance;
    }


    public static List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public void checkToAdd(Food food, double foodSheltLifePers) {
        if (foodSheltLifePers > 0.75) {
            foodList.add(food);
        }
    }
}
