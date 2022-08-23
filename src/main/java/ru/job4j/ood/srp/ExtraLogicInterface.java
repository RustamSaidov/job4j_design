package ru.job4j.ood.srp;

public interface ExtraLogicInterface {

    public void printInt(int i);

    public int getIntByFormula(float i);
}
/*
Комментарий к задаче. Какие принципы единственной ответственности нарушены в данном коде?
Интерфейс ExtraLogicExample имеет лишнюю логику и может как печатать число, так и рассчитывать квадрат этого числа.
 */