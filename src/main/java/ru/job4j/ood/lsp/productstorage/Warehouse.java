package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private List<Food> foodList = new ArrayList<>();

    Warehouse() {
    }

    public List<Food> getFoodList() {
        return new ArrayList<Food>(foodList);
    }

    @Override
    public boolean checkToAdd(Food food) {
        double foodSheltLifePers = getShelfLifePersent(food.getExpiryDate(), food.getCreateDate());
        boolean result = false;
        if (foodSheltLifePers > UPPER_SHELT_LIFE_PERS) {
            foodList.add(food);
            result = true;
        }
        return result;
    }
}
