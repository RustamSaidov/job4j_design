package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.ood.lsp.productstorage.ControlQuality.getDateDifferenceInDays;
import static ru.job4j.ood.lsp.productstorage.ControlQuality.getShelfLifePersentage;

class ControlQualityTest {

    @Test
    public void whenDiffInDaysIs10() {
        int diffNiDays = getDateDifferenceInDays(new GregorianCalendar(2021, Calendar.JANUARY, 30).getTime(),
                new GregorianCalendar(2021, Calendar.JANUARY, 20).getTime());
        assertThat(diffNiDays).isEqualTo(10);
    }

    @Test
    public void whenDiffInDaysIsLessThan0() {
        int diffNiDays = getDateDifferenceInDays(new GregorianCalendar(2021, Calendar.JANUARY, 20).getTime(),
                new GregorianCalendar(2021, Calendar.JANUARY, 30).getTime());
        assertThat(diffNiDays).isLessThan(0);
    }

    @Test
    public void whenShelfLifePersentageMoreThan75() {
        double sheltLifePers = getShelfLifePersentage(new GregorianCalendar(2023, Calendar.JANUARY, 20).getTime(),
                new GregorianCalendar(2022, Calendar.AUGUST, 20).getTime());
        assertThat(sheltLifePers).isGreaterThan(0.75);
    }

    @Test
    public void whenShelfLifePersentageBetween25and75() {
        double sheltLifePers = getShelfLifePersentage(new GregorianCalendar(2023, Calendar.JANUARY, 20).getTime(),
                new GregorianCalendar(2022, Calendar.MARCH, 20).getTime());
        assertThat(sheltLifePers).isLessThan(0.75);
        assertThat(sheltLifePers).isGreaterThan(0.25);
    }

    @Test
    public void whenShelfLifePersentageBetween0and25() {
        double sheltLifePers = getShelfLifePersentage(new GregorianCalendar(2022, Calendar.SEPTEMBER, 20).getTime(),
                new GregorianCalendar(2022, Calendar.MARCH, 20).getTime());
        assertThat(sheltLifePers).isLessThan(0.25);
        assertThat(sheltLifePers).isGreaterThan(0);
    }

    @Test
    public void whenShelfLifePersentageIsGone() {
        double sheltLifePers = getShelfLifePersentage(new GregorianCalendar(2022, Calendar.MARCH, 20).getTime(),
                new GregorianCalendar(2022, Calendar.SEPTEMBER, 20).getTime());
        assertThat(sheltLifePers).isGreaterThan(0);
    }

    @Test
    public void whenFoodDestributedToWarehouse() {
        Food food1 = new Food("chips", new GregorianCalendar(2025, Calendar.JANUARY, 30).getTime(),
                new GregorianCalendar(2022, Calendar.MARCH, 30).getTime(), 100, 0.50);

        ControlQuality controlQuality = new ControlQuality();
        List<Store> stores = new ArrayList<>();
        stores.add(Trash.getInstance());
        stores.add(Shop.getInstance());
        stores.add(Warehouse.getInstance());

        Warehouse.getFoodList().clear();
        Shop.getFoodList().clear();
        Trash.getFoodList().clear();

        controlQuality.distribute(food1, stores);
        assertThat(Warehouse.getFoodList().get(0)).isEqualTo(food1);
        assertThat(Shop.getFoodList().size()).isEqualTo(0);
        assertThat(Trash.getFoodList().size()).isEqualTo(0);
    }

    @Test
    public void whenFoodDestributedToShop() {
        Food food2 = new Food("chocolade", new GregorianCalendar(2023, Calendar.JANUARY, 30).getTime(),
                new GregorianCalendar(2022, Calendar.MARCH, 30).getTime(), 100, 0.50);

        ControlQuality controlQuality = new ControlQuality();
        List<Store> stores = new ArrayList<>();
        stores.add(Trash.getInstance());
        stores.add(Shop.getInstance());
        stores.add(Warehouse.getInstance());

        Warehouse.getFoodList().clear();
        Shop.getFoodList().clear();
        Trash.getFoodList().clear();

        controlQuality.distribute(food2, stores);
        assertThat(Shop.getFoodList().get(0)).isEqualTo(food2);
        assertThat(Warehouse.getFoodList().size()).isEqualTo(0);
        assertThat(Trash.getFoodList().size()).isEqualTo(0);

    }

    @Test
    public void whenFoodDestributedToShopAndPriceGetLower() {

        Food food3 = new Food("milk", new GregorianCalendar(2022, Calendar.SEPTEMBER, 30).getTime(),
                new GregorianCalendar(2022, Calendar.MARCH, 30).getTime(), 100, 0.20);

        ControlQuality controlQuality = new ControlQuality();
        List<Store> stores = new ArrayList<>();
        stores.add(Trash.getInstance());
        stores.add(Shop.getInstance());
        stores.add(Warehouse.getInstance());

        Warehouse.getFoodList().clear();
        Shop.getFoodList().clear();
        Trash.getFoodList().clear();

        controlQuality.distribute(food3, stores);
        assertThat(Shop.getFoodList().get(0)).isEqualTo(food3);
        assertThat(Shop.getFoodList().get(0).getPrice()).isEqualTo(80);
        assertThat(Warehouse.getFoodList().size()).isEqualTo(0);
        assertThat(Trash.getFoodList().size()).isEqualTo(0);
    }

    @Test
    public void whenFoodDestributedToTrash() {

        Food food4 = new Food("fish", new GregorianCalendar(2021, Calendar.JANUARY, 30).getTime(),
                new GregorianCalendar(2020, Calendar.MARCH, 30).getTime(), 100, 0.50);
        ControlQuality controlQuality = new ControlQuality();
        List<Store> stores = new ArrayList<>();
        stores.add(Trash.getInstance());
        stores.add(Shop.getInstance());
        stores.add(Warehouse.getInstance());

        Warehouse.getFoodList().clear();
        Shop.getFoodList().clear();
        Trash.getFoodList().clear();

        controlQuality.distribute(food4, stores);
        assertThat(Trash.getFoodList().get(0)).isEqualTo(food4);
        assertThat(Shop.getFoodList().size()).isEqualTo(0);
        assertThat(Warehouse.getFoodList().size()).isEqualTo(0);
    }
}