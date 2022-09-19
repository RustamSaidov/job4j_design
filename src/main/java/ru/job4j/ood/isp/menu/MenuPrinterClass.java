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
            String numberOfMenuItem = mii.getNumber();
            String name = mii.getName();
            String offset = createOffset(numberOfMenuItem);
            System.out.println(offset + numberOfMenuItem + name);
        }
    }

    private static String createOffset(String numberOfMenuItem) {
        int countOccurrences = numberOfMenuItem.split("\\.").length - 1;
        return SEPATATOR.repeat(countOccurrences);
    }
}
