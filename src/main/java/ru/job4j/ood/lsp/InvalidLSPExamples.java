package ru.job4j.ood.lsp;

import ru.job4j.collection.list.List;

import java.util.ArrayList;

public class InvalidLSPExamples {

    public class Airplane {
        int soundSpeed;
        String name;

        public Airplane(int soundSpeed, String name) {
            this.soundSpeed = soundSpeed;
            /*Валидация: */
            validate(name);
            this.name = name;
        }

        protected void validate(String name) {
            if (name.equals("")) {
                throw new IllegalArgumentException("Invalid airplane name!");
            }
        }

        public void setName(String name) {
            /*Валидация: */
            validate(name);
            this.name = name;
        }

        public void supersonicFlight(int maxSpeed, List<String> list) {
            ArrayList supersonicPlanes = (ArrayList) list;
            String result = "";
            /*Предусловие */
            if (soundSpeed <= maxSpeed) {
                System.out.println(name + " is supersonic plane.");
            } else {
                throw new IllegalArgumentException("This plane isn't supersonic!");
            }

            /*Постусловие */
            if (!supersonicPlanes.contains(name)) {
                supersonicPlanes.add(name);
            }
        }
    }

    /* НАРУШЕНИЕ LSP: 1. Предусловия (Preconditions) не могут быть усилены в подклассе*/
    public class SomeAirplane extends Airplane {

        public SomeAirplane(int soundSpeed, String name) {
            super(soundSpeed, name);
        }

        public void supersonicFlight(int maxSpeed, List<String> list) {
            ArrayList supersonicPlanes = (ArrayList) list;
            String result = "";
            /*Предусловие ужесточено*/
            if (soundSpeed == maxSpeed) {
                System.out.println(name + " is supersonic plane.");
            } else {
                throw new IllegalArgumentException("This plane isn't supersonic!");
            }

            /*Постусловие */
            if (!supersonicPlanes.contains(name)) {
                supersonicPlanes.add(name);
            }
        }
    }

    /* НАРУШЕНИЕ LSP: 2. Постусловия (Postconditions) не могут быть ослаблены в подклассе.*/
    public class SomeAirplane2 extends Airplane {

        public SomeAirplane2(int soundSpeed, String name) {
            super(soundSpeed, name);
        }

        public void supersonicFlight(int maxSpeed, List<String> list) {
            ArrayList supersonicPlanes = (ArrayList) list;
            String result = "";
            /*Предусловие */
            if (soundSpeed == maxSpeed) {
                System.out.println(name + " is supersonic plane.");
            } else {
                throw new IllegalArgumentException("This plane isn't supersonic!");
            }

            /*Постусловие ослаблено*/
            if (true) {
                supersonicPlanes.add(name);
            }
        }
    }

    /* НАРУШЕНИЕ LSP: 3. Инварианты (Invariants) — все условия базового класса также должны быть сохранены и в подклассе */
    public class SomeAirplane3 extends Airplane {

        public SomeAirplane3(int soundSpeed, String name) {
            super(soundSpeed, name);
        }

        protected void validate(String name) {
            if (name.equals("")) {
                throw new IllegalArgumentException("Invalid airplane name!");
            }
        }

        @Override
        public void setName(String name) {
            /*Валидация при переопределении куда-то пропала */
            this.name = name;
        }

        public void supersonicFlight(int maxSpeed, List<String> list) {
            ArrayList supersonicPlanes = (ArrayList) list;
            String result = "";
            /*Предусловие */
            if (soundSpeed <= maxSpeed) {
                System.out.println(name + " is supersonic plane.");
            } else {
                throw new IllegalArgumentException("This plane isn't supersonic!");
            }

            /*Постусловие */
            if (!supersonicPlanes.contains(name)) {
                supersonicPlanes.add(name);
            }
        }
    }
}
