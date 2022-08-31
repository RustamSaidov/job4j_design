package ru.job4j.ood.isp;

public class InvalidISPExample {

    /*ПРИМЕР 1. Неверно выделена абстракция. Не все птицы умеют летать: */
    public interface Bird {
        void run();

        void fly();

        Bird layAnEgg();
    }

    public class Eagle implements Bird {

        @Override
        public void run() {
            System.out.println("run!");
        }

        @Override
        public void fly() {
            System.out.println("fly!");
        }

        @Override
        public Eagle layAnEgg() {
            return new Eagle();
        }
    }

    /*Страус не умеет летатью Приходится затыкать метод затычкой*/
    public class Ostrich implements Bird {

        @Override
        public void run() {
            System.out.println("run!");
        }

        @Override
        public void fly() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Ostrich layAnEgg() {
            return new Ostrich();
        }
    }

/*ПРИМЕР 2. Слишком большое количество методов в Интерфейсе для Завода. НЕ все из заводов делают все эти услуги.
    Надо разбивать на множество интерфейсов с одним методом и наследоваться от них в каждом отдельном случае: */

    public interface Plant {
        void turning();

        void milling();

        void additing();

        void cutting();

        void laserCutting();

        void assembling();
    }

/*ПРИМЕР 3. Интерфейс для грузовых самолетов состоит из методов для двух разных типов военно-транспортных грузовых самолетов
- топливозаправщиков и грузовых. Они никак не пересекаются. Правильно было бы разделить на два интерфейса
- для грузовых самолетов и для топливозаправщиков: */

    public interface Cargoplane {

        /*методы, характерные для самолета с паращутированием грузов*/
        boolean openTheHatch();

        boolean dropTheCargo();

        boolean closeTheHatch();

        /*методы, характерные для самолета-топливозаправщика*/

        boolean extendTheFuelHosepipe();

        boolean beginRefuel();

        boolean removeTheFuelHosepipe();
    }
}
