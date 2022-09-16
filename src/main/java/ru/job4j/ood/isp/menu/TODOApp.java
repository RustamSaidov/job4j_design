package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    private static int choise = 1;
    private static Menu menu = new SimpleMenu();

    public static void main(String[] args) {
        while (choise != 0) {
            System.out.println("""
                    Выберите, что хотите сделать:
                    1 - Добавить элемент меню.
                    2 - Вывести меню.
                    0 - Выйти.
                    """);
            Scanner scanner = new Scanner(System.in);
            choise = scanner.nextInt();
            if (choise == 1) {
                System.out.println("Введите название родительского имени пункта меню (введите ROOT, если это верхеуровневый пункт меню):");
                scanner = new Scanner(System.in);
                String parentName = scanner.nextLine();
                System.out.println("Введите название пункта меню:");
                scanner = new Scanner(System.in);
                String childName = scanner.nextLine();
                if (parentName.equals("ROOT")) {
                    menu.add(Menu.ROOT, childName, System.out::println);
                } else {
                    menu.add(parentName, childName, System.out::println);
                }
            } else if (choise == 2) {
                MenuPrinterClass menuPrinterClass = new MenuPrinterClass();
                menuPrinterClass.print(menu);
            }
        }
    }
}
