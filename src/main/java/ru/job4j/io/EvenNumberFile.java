package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            /*System.out.println(text);*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] lines = text.toString().split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            if (Integer.parseInt(lines[i]) % 2 == 0) {
                System.out.println(lines[i] + " - even number");
            } else {
                System.out.println(lines[i] + " - odd number");
            }
        }
    }
}