package ru.job4j.ood.isp.rightExample;

interface Input {
    void in(String data);
}

interface Output {
    void output();
}

interface Calculator {
    void calculate();
}

interface Internet {
    void connect();
}

/* ПРАВИЛЬНАЯ РЕАЛИЗАЦИЯ. Класс реализуется от нескольких малых интерфейсов, только тех, которых нужно*/
class Server implements Calculator, Internet {

    @Override
    public void calculate() {
        System.out.println("Do work!");
    }

    @Override
    public void connect() {
        System.out.println("Try connect ...");
    }
}
