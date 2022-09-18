package ru.job4j.ood.isp.menu;

import java.util.Iterator;
import java.util.StringJoiner;

public class MenuPrinterClass implements MenuPrinter {
    public static final String SEPATATOR = " ";
    @Override
    public void print(Menu menu) {
        Iterator iterator = menu.iterator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo mii = (Menu.MenuItemInfo) iterator.next();
            String number = mii.getNumber();
            String name = mii.getName();
            String offset = createOffset(number);
            System.out.println(offset + number + name);
        }

    }

    private static long countOccurrences(String str, char ch) {
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }

    private static String createOffset(String number) {
        StringJoiner joiner = new StringJoiner("");
        for (int i = 0; i < countOccurrences(number, '.'); i++) {
            joiner.add(SEPATATOR);
        }
        return joiner.toString();
    }
}
