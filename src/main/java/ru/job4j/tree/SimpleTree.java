package ru.job4j.tree;

import ru.job4j.iterator.ListUtils;
import ru.job4j.iterator.SamplePredicate;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E, T> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public Optional<Node<E>> isBinary(E value) {
        Optional<Node<E>> result = Optional.empty();
        Optional<Node<E>> node = findBy(value);
        if (node.get().children.size() > 2) {
            result = node;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        EqualsPredicate<Node<E>> filter = new EqualsPredicate<>();
        filter.varc1 = new Node<>(value);
        return findByPredicate(filter);
    }

    public static class EqualsPredicate<T> implements Predicate<T> {
        T varc1;

        public boolean test(T varc) {
            return varc1.equals(varc);
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