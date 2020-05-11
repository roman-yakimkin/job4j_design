package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Односвязный список для хранения данных
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 08.05.2020
 * @version 1.0
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head = null;

    public void add(T value) {
        Node<T> node = new Node(value, null);
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

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        head = head.next;
        return result;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        if (head.next == null) {
            head = null;
            return result;
        }
        Node<T> tail = head;
        Node<T> tailPrev = head;
        while (tail.next != null) {
            tailPrev = tail;
            tail = tail.next;
        }
        result = tail.value;
        tailPrev.next = null;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> link = head;

            @Override
            public boolean hasNext() {
                return (link != null);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = link.value;
                link = link.next;
                return result;
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
