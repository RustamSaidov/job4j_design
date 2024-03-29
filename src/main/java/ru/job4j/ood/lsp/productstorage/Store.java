package ru.job4j.ood.lsp.productstorage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface Store {

    default double getShelfLifePersent(LocalDate futureDay, LocalDate day) {
        int shelfLife;
        int remainingShelfLife;
        double shelfLifePercentage;

        shelfLife = getDateDifferenceInDays(futureDay, day);
        remainingShelfLife = getDateDifferenceInDays(futureDay, LocalDate.now());
        shelfLifePercentage = (double) remainingShelfLife / shelfLife;
        return shelfLifePercentage;
    }

    default int getDateDifferenceInDays(LocalDate futureDay, LocalDate day) {
        LocalDateTime ldt1 = day.atStartOfDay();
        LocalDateTime ldt2 = futureDay.atStartOfDay();

        return (int) Duration.between(ldt1, ldt2).toDays();
    }

    boolean checkToAdd(Food food);

    public List<Food> getFoodList();

    public default boolean clearTheStorage(List<Food> foodList) {
        boolean result = false;
        foodList.clear();
        if (foodList.isEmpty()) {
            result = true;
        }
        return result;
    }
}
