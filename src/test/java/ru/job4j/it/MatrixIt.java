package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (row < data.length) {
            if (column < data[row].length) {
                result = true;
                break;
            }
            row++;
        }
        return result;
    }

    @Override
    public Integer next() {
        int result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column < data[row].length - 1) {
            result = data[row][column++];
        } else {
            result = data[row++][column];
            column = 0;
        }
        return result;
    }
}
