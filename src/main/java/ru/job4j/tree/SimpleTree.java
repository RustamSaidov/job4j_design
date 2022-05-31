package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (root.value == parent) {
            root.children.add(new Node<>(child));
            rsl = true;
        } else {
            rsl = searchInList(parent, child, rsl, root.children);
        }
        return rsl;
    }

    private boolean searchInList(E parent, E child, boolean rsl, List<Node<E>> childrenList) {
        if (childrenList.isEmpty()) {
            rsl = false;
        } else {
            for (int i = 0; i < childrenList.size(); i++) {
                Node<E> tempRoot = childrenList.get(i);
                if (parent == childrenList.get(i).value) {
                    tempRoot.children.add(new Node<>(child));
                    rsl = true;
                } else {
                    rsl = searchInList(parent, child, rsl, tempRoot.children);
                }
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}