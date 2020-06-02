package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collection.SimpleList;

import java.util.Iterator;

/**
 * Коллекция, работающая в многопоточной среде
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 02.06.2020
 * @version 1.0
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final SimpleList<T> list = new SimpleList<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    private SimpleList<T> copy(SimpleList<T> original) {
        SimpleList<T> result = new SimpleList<>();
        original.forEach(result::add);
        return result;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
