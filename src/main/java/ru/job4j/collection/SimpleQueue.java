package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inputCount = 0;
    private int outputCount = 0;

    public T poll() {
        if (inputCount == 0 && outputCount == 0) {
            throw new NoSuchElementException();
        }
        if (outputCount == 0) {
            for (long i = 0; i < inputCount; inputCount--) {
                out.push(in.pop());
                outputCount++;
            }
        }
        outputCount--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inputCount++;
    }
}