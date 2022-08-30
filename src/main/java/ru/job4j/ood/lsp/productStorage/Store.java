package ru.job4j.ood.lsp.productStorage;

import java.util.List;

public interface Store {
    List<Food> list = null;
    
    public void checkToAdd(Food food, double foodSheltLifePers);
}
