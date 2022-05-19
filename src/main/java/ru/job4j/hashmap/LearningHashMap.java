package ru.job4j.hashmap;

import java.util.Calendar;
import java.util.HashMap;

public class LearningHashMap {
    public static void main(String[] args) {
        User user1 = new User("John", 1, Calendar.getInstance());
        User user2 = new User("John", 1, Calendar.getInstance());
        HashMap<User, Object> hashMap = new HashMap<>();
        hashMap.put(user1, new Object());
        hashMap.put(user2, new Object());
        System.out.println(hashMap);
    }
}
