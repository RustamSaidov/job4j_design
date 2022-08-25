package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;

public class InvalidOCPExamples {
    /*Пример 1. Изменение идет не через расширение (Наследование), а через изменение: */
    /*Было:*/
    private static class CalcMachine {
        public int calculateSOmething(int i, int j) {
            return i * j;
        }
    }
    /*Стало:
    private static class CalcMachine {
        public int calculateSOmething(int i, int j) {
            return i * j * 222;
        }
    }
    */

    /*Пример 2. Изменение идет не через расширение (Наследование), а через изменение: */
    /*Было:*/
    private static class CalcMachine123 {
        public int calculateSOmething(int i, int j) {
            return i * j;
        }
    }
    /*Стало:
    private static class CalcMachine123 {
        public int calculateSOmething(int i, int j) {
            return i * j;
        }

        public int calculateNew(int i, int j) {
            return i * j * 222;
        }
    }
    */

    /*Пример 3. Изменение идет через наследование, но типы объектов не предполагают принадлежность к единой иерархии: */
    private static class Bird {
        public int calculateSpeedOfFlight(int i, int j) {
            return i * j;
        }
    }

    private static class Wolf extends Bird {
        public int calculateSpeedOfFlight(int i, int j) {
            return i * j;
        }
    }

    /*Пример 4. Поля реализованы, вместо того чтобы быть представленными абстракцией. Возвращаемое тип так же указан: */
    private static class MultiplySomething4 {

        private ArrayList<Integer> numbers;

        private double coeff = 0.5;

        public MultiplySomething4(int coeff) {
            this.coeff = coeff;
        }

        public void addNumberToList(int i, int j) {
            numbers.add(i * j);
        }
    }

    /*Пример 5. Параметры реализованы, вместо того чтобы быть представленными абстракцией: */
    private static class MultiplySomething5 {

        private double coeff = 0.5;

        public MultiplySomething5(int coeff) {
            this.coeff = coeff;
        }

        public void addNumberToList(int i, int j, ArrayList<Integer> numbers) {
            numbers.add(i * j);
        }
    }

    /*Пример 6. Возвращаемый тип реализован, вместо того чтобы быть представленными абстракцией: */
    private static class MultiplySomething6 {

        private double coeff = 0.5;

        public MultiplySomething6(int coeff) {
            this.coeff = coeff;
        }

        public ArrayList<Integer> getFullList(int i, int j) {
            ArrayList<Integer> numbers = new ArrayList<>();
            numbers.add(i * j);
            return numbers;
        }
    }
}
