package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {

        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < getSimple2DArray().length; i++) {
                for (int j = 0; j < getSimple2DArray().length; j++) {
                    out.write((getSimple2DArray()[i][j] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[][] getSimple2DArray() {
        int[][] simple2DArray = new int[4][4];
        for (int i = 0; i < simple2DArray.length; i++) {
            for (int j = 0; j < simple2DArray[0].length; j++) {
                simple2DArray[i][j] = (i + 1) * (j + 1);
            }
        }
        return simple2DArray;
    }
}