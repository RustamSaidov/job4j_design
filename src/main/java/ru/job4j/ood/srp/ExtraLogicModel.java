package ru.job4j.ood.srp;

import java.util.Objects;

public class ExtraLogicModel {
    public int number;
    public String name;

    public ExtraLogicModel(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int returnSomeNumber(int i) {
        return Math.round(i * i + 222);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtraLogicModel)) {
            return false;
        }
        ExtraLogicModel that = (ExtraLogicModel) o;
        return getNumber() == that.getNumber() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getName());
    }
}
/*
Комментарий к задаче. Данная модель данных с лишней логикой.
1. Она не только описывает модель (поля, конструкторы, геттеры-сеттеры), но и имеет функционал работы с данными - метод returnSomeNumber.
 */
