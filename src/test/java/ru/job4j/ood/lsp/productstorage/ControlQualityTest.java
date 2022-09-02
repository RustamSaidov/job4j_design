package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    LocalDate uniDate = LocalDate.now();

    @Test
    public void whenDiffInDaysIs10() {
        Store store = new Warehouse();
        int diffNiDays = store.getDateDifferenceInDays(uniDate,
                uniDate.minusDays(10));
        assertThat(diffNiDays).isEqualTo(10);
    }

    @Test
    public void whenDiffInDaysIs60() {
        Store store = new Warehouse();
        int diffNiDays = store.getDateDifferenceInDays(uniDate,
                uniDate.minusDays(60));
        assertThat(diffNiDays).isEqualTo(60);
    }

    @Test
    public void whenDiffInDaysIsLessThan0() {
        Store store = new Warehouse();
        int diffNiDays = store.getDateDifferenceInDays(uniDate.withDayOfMonth(1),
                uniDate);
        assertThat(diffNiDays).isLessThan(0);
    }

    @Test
    public void whenShelfLifePersentageMoreThan75() {
        Store store = new Warehouse();
        double sheltLifePers = store.getShelfLifePersent(uniDate.plusYears(5),
                uniDate);
        assertThat(sheltLifePers).isGreaterThan(0.75);
    }

    @Test
    public void whenShelfLifePersentageBetween25and75() {
        Store store = new Warehouse();
        double sheltLifePers = store.getShelfLifePersent(uniDate.plusYears(1),
                uniDate.minusYears(1));
        assertThat(sheltLifePers).isLessThan(0.75);
        assertThat(sheltLifePers).isGreaterThan(0.25);
    }

    @Test
    public void whenShelfLifePersentageBetween0and25() {
        Store store = new Warehouse();
        double sheltLifePers = store.getShelfLifePersent(uniDate.plusDays(1),
                uniDate.minusYears(1));
        assertThat(sheltLifePers).isLessThan(0.25);
        assertThat(sheltLifePers).isGreaterThan(0);
    }

    @Test
    public void whenShelfLifePersentageIsGone() {
        Store store = new Warehouse();
        double sheltLifePers = store.getShelfLifePersent(uniDate.minusDays(1),
                uniDate);
        assertThat(sheltLifePers).isGreaterThan(0);
    }

    @Test
    public void whenFoodDestributedToWarehouse() {
        Food food1 = new Food("chips", uniDate.plusYears(3),
                uniDate, 100, 0.50);

        List<Store> stores = new ArrayList<>();
        ControlQuality controlQuality = new ControlQuality(stores);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);

        warehouse.getFoodList().clear();
        shop.getFoodList().clear();
        trash.getFoodList().clear();

        controlQuality.distribute(food1);
        assertThat(warehouse.getFoodList()).containsAll(List.of(food1));
        assertThat(shop.getFoodList().size()).isEqualTo(0);
        assertThat(trash.getFoodList().size()).isEqualTo(0);
    }

    @Test
    public void whenFoodDestributedToShop() {
        Food food2 = new Food("chocolade", uniDate.plusYears(1),
                uniDate.minusYears(1), 100, 0.50);
        List<Store> stores = new ArrayList<>();
        ControlQuality controlQuality = new ControlQuality(stores);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);

        warehouse.getFoodList().clear();
        shop.getFoodList().clear();
        trash.getFoodList().clear();

        controlQuality.distribute(food2);
        assertThat(shop.getFoodList()).containsAll(List.of(food2));
        assertThat(warehouse.getFoodList().size()).isEqualTo(0);
        assertThat(trash.getFoodList().size()).isEqualTo(0);
    }

    @Test
    public void when2FoodDestributedToShop() {
        Food food11 = new Food("chocolade", uniDate.plusYears(1),
                uniDate.minusYears(1), 100, 0.50);
        Food food22 = new Food("Candies", uniDate.plusYears(1),
                uniDate.minusYears(1), 200, 0.50);
        List<Store> stores = new ArrayList<>();
        ControlQuality controlQuality = new ControlQuality(stores);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);

        warehouse.getFoodList().clear();
        shop.getFoodList().clear();
        trash.getFoodList().clear();

        controlQuality.distribute(food11);
        controlQuality.distribute(food22);
        assertThat(shop.getFoodList()).containsAll(List.of(food11, food22));
        assertThat(warehouse.getFoodList().size()).isEqualTo(0);
        assertThat(trash.getFoodList().size()).isEqualTo(0);
    }


    @Test
    public void whenFoodDestributedToShopAndPriceGetLower() {
        Food food3 = new Food("milk", uniDate.plusDays(1),
                uniDate.minusMonths(1), 100, 0.20);
        List<Store> stores = new ArrayList<>();
        ControlQuality controlQuality = new ControlQuality(stores);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);

        warehouse.getFoodList().clear();
        shop.getFoodList().clear();
        trash.getFoodList().clear();

        controlQuality.distribute(food3);
        assertThat(shop.getFoodList()).containsAll(List.of(food3));
        assertThat(shop.getFoodList().get(0).getPrice()).isEqualTo(80);
        assertThat(warehouse.getFoodList().size()).isEqualTo(0);
        assertThat(trash.getFoodList().size()).isEqualTo(0);
    }

    @Test
    public void whenFoodDestributedToTrash() {
        Food food4 = new Food("fish", uniDate.minusMonths(1),
                uniDate.minusMonths(2), 100, 0.50);
        List<Store> stores = new ArrayList<>();
        ControlQuality controlQuality = new ControlQuality(stores);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);

        warehouse.getFoodList().clear();
        shop.getFoodList().clear();
        trash.getFoodList().clear();

        controlQuality.distribute(food4);
        assertThat(trash.getFoodList()).containsAll(List.of(food4));
        assertThat(shop.getFoodList().size()).isEqualTo(0);
        assertThat(warehouse.getFoodList().size()).isEqualTo(0);
    }
}