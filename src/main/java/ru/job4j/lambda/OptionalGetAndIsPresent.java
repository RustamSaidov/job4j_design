package ru.job4j.lambda;

import java.util.*;

public class OptionalGetAndIsPresent {

    public static int get(int[] data, int el) {
        Optional optional = indexOf(data, el);
        return optional.isPresent() ? (int) optional.get() : -1;

    }

    private static Optional<Integer> indexOf(int[] data, int el) {
        List<Integer> list = new ArrayList<>();

//        for(int i=0; i<data.length; i++){
//            System.out.println("777"+i);
//            list.add(data[i]);
//        }
//
//
//        //Collections.addAll(list, data);
//
//        for(int i=0; i<list.size(); i++){
//            System.out.println("!!!"+i);
//        }
//        System.out.println(Arrays.toString(list.toArray()));
//        System.out.println(Optional.of(list.indexOf(el)));
        return Arrays.stream(data).findAny().isPresent() ? Optional.of(data.) : Optional.empty();
//        return list.contains(el) ? Optional.of(list.indexOf(el)) : Optional.empty();
    }
}
