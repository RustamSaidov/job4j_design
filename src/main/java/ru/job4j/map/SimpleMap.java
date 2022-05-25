package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        if (count / capacity >= LOAD_FACTOR) {
            MapEntry<K, V>[] tempTable = new MapEntry[capacity * 2];
            for (int i = 0; i < table.length; i++) {
                int index = indexFor(hash(table[i].key.hashCode()));
                tempTable[index] = table[i];
            }
            table = tempTable;
        }
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> result;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            result = table[index].key.equals(key) ? table[indexFor(hash(key.hashCode()))] : null;
        } else {
            result = null;
        }
        return result.value;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        for (int i = 0; i < table.length; i++) {
            if (table[index] == table[i]) {
                if (table[index].key.equals(key)) {
                    table[i] = null;
                    count--;
                    modCount++;
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<K>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}