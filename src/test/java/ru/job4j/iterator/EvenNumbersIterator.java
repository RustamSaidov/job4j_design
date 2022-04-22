package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = 0; i < data.length; i++) {
            if (index < data.length) {
                if (data[index] % 2 == 0) {
                    return true;
                } else index++;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    void forEachRemaining() {
        throw new UnsupportedOperationException("forEachRemaining");
    }
}