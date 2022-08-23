package ru.job4j.ood.srp;

public class ExtraLogicExample implements ExtraLogicInterface {
    @Override
    public void printInt(int i) {
        System.out.println(i);
    }

    @Override
    public int getIntByFormula(float i) {
        return Math.round(i * i + 222);
    }
}
/*
Комментарий к задаче. Какие принципы SRP в данном коде?
1. Метод getIntByFormula знает как конкретно посчитать и вернуть по формуле число.
 */