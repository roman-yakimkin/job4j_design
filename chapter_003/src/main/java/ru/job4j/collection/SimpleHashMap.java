package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Простая реализация карты
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 11.05.2020
 * @version 1.0
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
    HashMapElement[] data;
    int modCount = 0;

    public SimpleHashMap(int size) {
        data = new HashMapElement[size];
    }

    public SimpleHashMap() {
        data = new HashMapElement[16];
    }

    private void expand() {
        HashMapElement[] newData = new HashMapElement[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private int getHashCode(K key) {
        return key.hashCode();
    }

    private int getHashIndex(K key) {
        return Math.abs(getHashCode(key) % data.length);
    }

    private V getInList(int index, K key) {
        Entry<K, V> e = (Entry<K, V>) data[index].entry;
        while (e != null) {
            if (e.key.equals(key)) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    private void addToList(int index, K key, V value) {
        Entry<K, V> e = (Entry<K, V>) data[index].entry;
        Entry<K, V> newEntry = new Entry(key, value, e);
        data[index].entry = newEntry;
        data[index].count++;
        modCount++;
    }

    private void replaceInList(int index, K key, V value) {
       Entry<K, V> e = (Entry<K, V>) data[index].entry;
       while (e != null) {
           if (e.key.equals(key)) {
               e.value = value;
               modCount++;
               return;
           }
           e = e.next;
       }
    }

    private void removeFromList(int index, K key) {
        Entry<K, V> e = (Entry<K, V>) data[index].entry;
        Entry<K, V> prevNode = null;
        while (e != null) {
            if (e.key.equals(key)) {
                if (prevNode == null) {
                    data[index].entry = e.next;
                } else {
                    prevNode.next = e.next;
                }
                data[index].count--;
                modCount++;
                return;
            }
            prevNode = e;
            e = e.next;
        }
    }

    public void put(K key, V value) {
        int hashIndex = getHashIndex(key);
        if (get(key) != null) {
            removeFromList(hashIndex, key);
            addToList(hashIndex, key, value);
        } else {
            if (data[hashIndex] == null) {
                data[hashIndex] = new HashMapElement<K, V>(getHashCode(key), new Entry<K, V>(key, value, null));
                modCount++;
            } else {
                addToList(hashIndex, key, value);
            }
        }
    }

    public V get(K key) {
        int hashIndex = getHashIndex(key);
        if (data[hashIndex] == null) {
            return null;
        }
        return (V) getInList(hashIndex, key);
    }

    public boolean delete(K key) {
        int hashIndex = getHashIndex(key);
        if (data[hashIndex] == null) {
            return false;
        }
        removeFromList(hashIndex, key);
        if (data[hashIndex].count == 0) {
            data[hashIndex] = null;
        }
        return true;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            private int index = 0;
            private Entry pointer = null;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < data.length && pointer == null) {
                    if (data[index] != null) {
                        pointer = data[index].entry;
                    }
                    if (pointer == null) {
                        index++;
                    }
                }
                return pointer != null;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Entry<K, V> result = pointer;
                pointer = pointer.next;
                if (pointer == null) {
                    index++;
                }
                return result;
            }
        };
    }

    public static class HashMapElement<K, V> implements Comparable {
        int hashCode;
        int count = 0;
        Entry<K, V> entry;
        public HashMapElement(int hashCode, Entry<K, V> entry) {
            this.hashCode = hashCode;
            this.entry = entry;
            if (entry != null) {
                count++;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof HashMapElement)) {
                return false;
            }
            HashMapElement<?, ?> that = (HashMapElement<?, ?>) o;
            return hashCode == that.hashCode;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public int compareTo(Object o) {
            return this.hashCode - o.hashCode();
        }
    }

    public static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;
        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
