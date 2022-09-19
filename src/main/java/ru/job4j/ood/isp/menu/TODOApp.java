package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final String INITIAL_MENU = """
            Выберите, что хотите сделать:
            1 - Добавить элемент меню.
            2 - Вывести меню.
            0 - Выйти.
            """;

    public static final String SET_PARENT_NAME = "Введите название родительского имени пункта меню (введите ROOT, если это верхеуровневый пункт меню):";
    public static final String SET_CHILD_NAME = "Введите название пункта меню:";
    public static final int ADD_MENU_ITEM = 1;
    public static final int SHOW_MENU = 2;
    public static final int EXIT = 0;

    private static Menu menu = new SimpleMenu();

    public static void main(String[] args) {
        int choise = 1;
        Scanner scanner = new Scanner(System.in);
        MenuPrinterClass menuPrinterClass = new MenuPrinterClass();
        while (choise != EXIT) {
            System.out.println(INITIAL_MENU);
            choise = scanner.nextInt();
            if (choise == ADD_MENU_ITEM) {
                System.out.println(SET_PARENT_NAME);
                String parentName = scanner.nextLine();
                System.out.println(SET_CHILD_NAME);
                String childName = scanner.nextLine();
                if (parentName.equals("ROOT")) {
                    menu.add(Menu.ROOT, childName, System.out::println);
                } else {
                    menu.add(parentName, childName, System.out::println);
                }
            } else if (choise == SHOW_MENU) {
                menuPrinterClass.print(menu);
            }
        }
    }
}
