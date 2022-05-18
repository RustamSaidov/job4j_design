package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean addResult = !contains(value);
        if (addResult) {
            set.add(value);
        }
        return addResult;
    }

    @Override
    public boolean contains(T value) {
        boolean isContains = false;
        for (T elt : set) {
            if (Objects.equals(elt, value)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}