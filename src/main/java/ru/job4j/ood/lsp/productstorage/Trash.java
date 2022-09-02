package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    public static final double END_SHELT_LIFE_PERS = 0;

    private List<Food> foodList = new ArrayList<>();

    public List<Food> getFoodList() {
        return new ArrayList<Food>(foodList);
    }

    @Override
    public boolean checkToAdd(Food food) {
        double foodSheltLifePers = getShelfLifePersent(food.getExpiryDate(), food.getCreateDate());
        boolean result = false;
        if (foodSheltLifePers < END_SHELT_LIFE_PERS) {
            foodList.add(food);
            result = true;
        }
        return result;
    }
}
