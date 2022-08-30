package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private static Shop instance;
    private static List<Food> foodList;

    private Shop() {
    }

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
            foodList = new ArrayList<>();
        }
        return instance;
    }


    public static List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public void checkToAdd(Food food, double foodSheltLifePers) {
        if (0.25 < foodSheltLifePers && foodSheltLifePers < 0.75) {
            foodList.add(food);
        } else if (0 < foodSheltLifePers && foodSheltLifePers < 0.25) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount());
            foodList.add(food);
        }
    }
}
