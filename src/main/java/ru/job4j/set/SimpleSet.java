package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.ListIterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean addResult = false;
        if(!contains(value)){
            set.add(value);
            addResult = true;
        }
        return addResult;
    }

    @Override
    public boolean contains(T value) {
        boolean isContains = false;
        for (T elt : set) {
            if(elt==null && value == null){
                isContains = true;
            }else if (elt.equals(value)) {
                isContains = true;
            }
        }
        return isContains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}