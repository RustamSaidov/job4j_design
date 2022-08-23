package ru.job4j.ood.srp;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SRPViolationExample {

    private List list;

    public SRPViolationExample(List list) {
        this.list = list;
    }

    public String getSomeString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public boolean saveInList(String str) {
        list.add(str);
        return list.contains(str);
    }
}
/*
Комментарий к задаче. Какие принципы единственной ответственности нарушены в данном коде?
1. Класс SRPViolationExample не наследуется ни от какого интерфейса, а значит он не сможет работать
вместе с другими классами, имеющими такой же функционал. Это так же нарушение критерия Инверсии зависимостей.
2. Класс имеет более одного метода. Он умеет и создавать строки, и записывать их в список, и даже проверять внутри
другого метода, имеется ли запись в списке.
3. Класс заранее знает какой тип коллекции будет использоваться, а так же какой тип объекта будет записываться в коллекцию.
 */