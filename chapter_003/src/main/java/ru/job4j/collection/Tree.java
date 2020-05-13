package ru.job4j.collection;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * Класс - дерево
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 13.05.2020
 * @version 1.0
 * @param <E> - тип элемента, хранящегося в дереве
 */
public class Tree<E> implements SimpleTree<E> {
    private Node<E> root;

    Tree(E root) {
        this.root = new Node<>(root);
    }

    private boolean isBinarySubTree(Node<E> root) {
        return (root.children.size() <= 2 && root.children.stream().allMatch(this::isBinarySubTree));
    }

    public boolean isBinary() {
        return isBinarySubTree(root);
    }

    @Override
    public boolean add(E parent, E child) {
        var parentNode = findBy(parent);
        if (parentNode.isEmpty() || findBy(child).isPresent()) {
            return false;
        }
        parentNode.get().children.add(new Node<E>(child));
        return true;
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
