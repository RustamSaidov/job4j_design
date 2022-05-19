package ru.job4j.hashmap;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class LearningHashMap {
    public static void main(String[] args) {
        Calendar birthdayData = new GregorianCalendar(2017, 0, 25);
        User user1 = new User("John", 1, birthdayData);
        User user2 = new User("John", 1, birthdayData);
        HashMap<User, Object> hashMap = new HashMap<>();
        hashMap.put(user1, new Object());
        hashMap.put(user2, new Object());
        System.out.println("hashcode 2-х юзеров. Hashcode переопределен: ");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println("Значение поля birthday 2-х юзеров. Hashcode переопределен: ");
        System.out.println(user1.birthday);
        System.out.println(user2.birthday);
        System.out.println(hashMap);
    }
}
