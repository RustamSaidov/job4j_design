package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private static Trash instance;
    private static List<Food> FOOD_LIST;

    private Trash() {
    }

    public static Trash getInstance() {
        if (instance == null) {
            instance = new Trash();
            FOOD_LIST = new ArrayList<>();
        }
        return instance;
    }


    public static List<Food> getFoodList() {
        return FOOD_LIST;
    }

    @Override
    public void checkToAdd(Food food, double foodSheltLifePers) {
        if (foodSheltLifePers < 0) {
            FOOD_LIST.add(food);
        }
    }
}
