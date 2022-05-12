package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public boolean revert() {
        boolean result = true;
        if (head == null || head.next == null) {
            result = false;
        } else {
            Node<T> previous = null;
            Node<T> current;
            Node<T> following;
            current = head;
            while (current != null) {
                following = current.next;
                current.next = previous;
                previous = current;
                current = following;
            }
            head = previous;
        }
        return result;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        Node<T> newHead = new Node<T>(value, head);
        head = newHead;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> newHead = head.next;
        T deleted = head.value;
        head.value = null;
        head.next = null;
        head = newHead;
        return deleted;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}