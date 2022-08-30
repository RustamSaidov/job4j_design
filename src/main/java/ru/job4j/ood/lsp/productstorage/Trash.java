package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private static Trash instance;
    private static List<Food> foodList;

    private Trash() {
    }

    public static Trash getInstance() {
        if (instance == null) {
            instance = new Trash();
            foodList = new ArrayList<>();
        }
        return instance;
    }


    public static List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public void checkToAdd(Food food, double foodSheltLifePers) {
        if (foodSheltLifePers < 0) {
            foodList.add(food);
        }
    }
}
