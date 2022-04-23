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
        return row < data.length && column < data[column].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
//        for(int i=row; i<data.length; i++) {
//            for (int j=column; j <data[row].length; j++) {
//                return data[column++][row];
//            }
//            row++;
//            }
//        return data[column++][row];
    }
//                if (column < data[0].length - 1) {
//                    return data[row][column++];
//                }else if(column == data[0].length - 1) {
//                    row++;
//                    column=0;
//                    return data[row-1][data[0].length - 1];
//                }
//        return data[row][column];
//        }
}
