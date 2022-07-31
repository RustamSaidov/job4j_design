package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class SordDemoClass {
    private int choise;
    private RandomArray randomArray;

    public static void main(String[] args) {
        SordDemoClass sdc = new SordDemoClass();
        System.out.println("-------------------------------------------");
        System.out.println("Добро пожаловать в демонстратор сортировок.");
        System.out.println("-------------------------------------------");

        while (sdc.choise != 5) {
            System.out.println("Выберите номер действия из меню ниже:");
            System.out.println("1 - Создание массива");
            System.out.println("2 - Сортировка пузырьком");
            System.out.println("3 - Сортировка вставками");
            System.out.println("4 - Сортировка слиянием ");
            System.out.println("5 - Выход.");
            Scanner scanner = new Scanner(System.in);
            sdc.choise = scanner.nextInt();

            switch (sdc.choise) {
                case 1:
                    RandomArray randomArr = new RandomArray(new Random());
                    randomArr.insert(250000);
                    sdc.randomArray = randomArr;
                    break;
                case 2:
                    BubbleSort bubbleSort = new BubbleSort();
                    bubbleSort.sort(sdc.randomArray);
                    break;
                case 3:
                    InsertSort insertSort = new InsertSort();
                    insertSort.sort(sdc.randomArray);
                    break;
                case 4:
                    MergeSort mergeSort = new MergeSort();
                    mergeSort.sort(sdc.randomArray);
                default:
                    break;
            }
        }
    }
}
