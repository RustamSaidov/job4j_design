package ru.job4j.tree;

import java.util.*;


public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public boolean isBinary() {
        IsBinaryPredicate<Node<E>> filter = new IsBinaryPredicate<>();
        return findByPredicate(filter).isEmpty();
    }

    public class IsBinaryPredicate<T> implements Predicate<T> {
        public boolean test(T t) {
            Tree.Node<E> el = (Node<E>) t;
            return el.children.size() > 2;
        }
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        EqualsPredicate<Node<E>> filter = new EqualsPredicate<>();
        filter.t1 = new Node<>(value);
        return findByPredicate(filter);
    }

    public static class EqualsPredicate<T> implements Predicate<T> {
        T t1;

        public boolean test(T t) {
            return t1.equals(t);
        }
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(parent).isPresent() && findBy(child).isEmpty()) {
            Node<E> tempRoot = findBy(parent).get();
            tempRoot.children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }
}