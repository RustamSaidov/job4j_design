package ru.job4j.ood.lsp.productstorage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    public static final double UPPER_SHELT_LIFE_PERS = 0.75;
    public static final double LOWER_SHELT_LIFE_PERS = 0.25;
    public static final double END_SHELT_LIFE_PERS = 0;

    private List<Food> foodList = new ArrayList<>();

    public List<Food> getFoodList() {
        return new ArrayList<Food>(foodList);
    }

    @Override
    public boolean checkToAdd(Food food) {
        double foodSheltLifePers = getShelfLifePersent(food.getExpiryDate(), food.getCreateDate());
        boolean result = false;
        if (LOWER_SHELT_LIFE_PERS < foodSheltLifePers && foodSheltLifePers < UPPER_SHELT_LIFE_PERS) {
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
