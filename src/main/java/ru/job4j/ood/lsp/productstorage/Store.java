package ru.job4j.ood.lsp.productstorage;

import java.util.List;

public interface Store {
    List<Food> FOOD_LIST = null;

    public void checkToAdd(Food food, double foodSheltLifePers);
}
