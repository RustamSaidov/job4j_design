package ru.job4j.ood.lsp.productStorage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store{
    private static Trash INSTANCE;
    private static List<Food> foodList;

    private Trash() {}

    public static Trash getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Trash();
            foodList = new ArrayList<>();
        }
        return INSTANCE;
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
