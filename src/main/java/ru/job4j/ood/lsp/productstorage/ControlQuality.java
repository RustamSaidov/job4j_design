package ru.job4j.ood.lsp.productstorage;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class ControlQuality {
    private List<Store> stores;


    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).checkToAdd(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> tempStorage = new ArrayList<>();
        for (Store store : stores) {
            tempStorage.addAll(store.getFoodList());
            store.clearTheStorage(store.getFoodList());
        }
        for (Food food : tempStorage) {
            distribute(food);
        }
    }
}
