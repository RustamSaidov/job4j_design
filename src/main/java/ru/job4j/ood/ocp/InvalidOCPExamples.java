package ru.job4j.ood.ocp;

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
    private static class MultiplySomething {
        private double coeff = 0.5;

        public MultiplySomething(int coeff) {
            this.coeff = coeff;
        }

        public double calculateSpeedOfFlight(int i, int j) {
            return i * j * coeff;
        }
    }
}
