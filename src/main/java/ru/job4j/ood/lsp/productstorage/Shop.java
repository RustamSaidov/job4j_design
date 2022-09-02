package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> foodList = new ArrayList<>();

    Shop() {
    }

    public List<Food> getFoodList() {
        return new ArrayList<Food>(foodList);
    }

    @Override
    public boolean checkToAdd(Food food) {
        double foodSheltLifePers = getShelfLifePersent(food.getExpiryDate(), food.getCreateDate());
        boolean result = false;
        if (0.25 < foodSheltLifePers && foodSheltLifePers < UPPER_SHELT_LIFE_PERS) {
            foodList.add(food);
            result = true;
        } else if (END_SHELT_LIFE_PERS < foodSheltLifePers && foodSheltLifePers < LOWER_SHELT_LIFE_PERS) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount());
            foodList.add(food);
            result = true;
        }
        return result;
    }
}
