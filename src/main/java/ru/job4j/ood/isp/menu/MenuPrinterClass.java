package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class MenuPrinterClass implements MenuPrinter{
    @Override
    public void print(Menu menu) {
        Iterator iterator = menu.iterator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo mii = (Menu.MenuItemInfo) iterator.next();
            String number = mii.getNumber();
            String name = mii.getName();
            System.out.println(number+name);
        }

    }
}
