package ru.job4j.ood.srp;

public class AnotherSRPViolationExample implements CalcInterface {

    @Override
    public int calculate(int[] array) {
        int result = 0;
        for (int i : array) {
            if (i % 2 == 0) {
                result = +i;
            }
        }
        return result;
    }
}
/*
Комментарий к задаче. Какие принципы единственной ответственности нарушены в данном коде?
1. У нас создана конкретная реализация метода calculate, в котором определено каким именно образом происходит расчет
и по каким критериям.
 */