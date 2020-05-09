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

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
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

    /**
     * Перевернуть фрагмент списка
     * @param pointerHead - начало списка
     * @param pointerTailNext - конец списка
     */
    private void revertSubList(Node<T> pointerHead, Node<T> pointerTailNext) {
        if (pointerHead == null || pointerHead.next == pointerTailNext || pointerHead == pointerTailNext) {
            return;
        }
        Node<T> pointerTail = pointerHead;
        while (pointerTail.next != pointerTailNext) {
            pointerTail = pointerTail.next;
        }
        T tmp = pointerHead.value;
        pointerHead.value = pointerTail.value;
        pointerTail.value = tmp;
        revertSubList(pointerHead.next, pointerTail);
    }

    /**
     * Перевернуть список
     */
    public void revert() {
        revertSubList(head, null);
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
