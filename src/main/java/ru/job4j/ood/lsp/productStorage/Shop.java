package ru.job4j.ood.lsp.productStorage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store{
    private static Shop INSTANCE;
    private static List<Food> foodList;

    private Shop() {}

    public static Shop getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Shop();
            foodList = new ArrayList<>();
        }
        return INSTANCE;
    }


    public static List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public void checkToAdd(Food food, double foodSheltLifePers) {
        if (0.25 < foodSheltLifePers && foodSheltLifePers< 0.75){
            foodList.add(food);
        } else if (0 < foodSheltLifePers && foodSheltLifePers< 0.25){
            food.setPrice(food.getPrice()*food.getDiscount());
            foodList.add(food);
        }
    }
}
