package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс - список для хранения данных
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 08.05.2020
 * @version 1.0
 */
public class SimpleList<T> implements Iterable<T> {
    /**
     * Структура для хранения списка
     */
    private Node<T> head = null;
    /**
     * Количество элементо в списке
     */
    private int count = 0;
    /**
     * Счетчик операций
     */
    private int operations = 0;

    /**
     * Добавить элемент в список
     * @param value - добавляемый элемент
     */
    public void add(T value) {
        Node<T> node = new Node(value, null);
        count++;
        operations++;
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

    /**
     * Получить элемент в списке по индексу
     * @param index - индекс элемента
     * @return - элемент
     */
    public T get(int index) {
        Objects.checkIndex(index, count);
        Node<T> node = head;
        int i = 0;
        while (i < index) {
            node = node.next;
            i++;
        }
        return node.value;
    }

    /**
     * Вернуть итератор
     * @return - итератор списка
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int op = operations;
            Node<T> link = head;

            @Override
            public boolean hasNext() {
                if (op != operations) {
                    throw new ConcurrentModificationException();
                }
                return link != null;
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

    /**
     * Структура узла списка
     * @param <T> - тип данных, хранящихся в узле
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
