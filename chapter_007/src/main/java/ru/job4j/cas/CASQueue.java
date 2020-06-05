package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Реализация многопоточной очереди
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 05.06.2020
 * @version 1.0
 */
@ThreadSafe
public class CASQueue<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    private final AtomicReference<Node<T>> tail = new AtomicReference<>();

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        Node<T> refTail;
        head.compareAndSet(null, temp);
        synchronized (this) {
            do {
                refTail = tail.get();
                if (refTail != null) {
                    refTail.next = temp;
                }
            } while (!tail.compareAndSet(refTail, temp));
        }
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalStateException("Queue is empty");
            }
            temp = ref.next;
        } while (!head.compareAndSet(ref, temp));
        ref.next = null;
        return ref.value;
    }

    private static final class Node<T> {
        final T value;
        Node<T> next;
        public Node(final T value) {
            this.value = value;
        }
    }
}
