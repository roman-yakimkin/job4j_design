package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Простая реализация карты
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 11.05.2020
 * @version 1.0
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
    HashMapElement[] data;
    int count = 0;
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
    }

    private void replaceInList(int index, K key, V value) {
       Entry<K, V> e = (Entry<K, V>) data[index].entry;
       while (e != null) {
           if (e.key.equals(key)) {
               e.value = value;
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
                return;
            }
            prevNode = e;
            e = e.next;
        }
    }

    public void put(K key, V value) {
        if (count == data.length - 1) {
            expand();
        }
        if (get(key) == null) {
            HashMapElement<K, V> element = new HashMapElement<>(getHashCode(key), new Entry<K, V>(key, value, null));
            int searchResult = Arrays.binarySearch(data, 0, count, element);
            if (searchResult >= 0) {
                removeFromList(searchResult, key);
                addToList(searchResult, key, value);
            } else {
                int elementPosition = -(Arrays.binarySearch(data, 0, count, element) + 1);
                System.arraycopy(data, elementPosition, data, elementPosition + 1, count - elementPosition + 1);
                data[elementPosition] = element;
                count++;
            }
        }
    }

    public V get(K key) {
        int hashCode = getHashCode(key);
        HashMapElement elem = new HashMapElement(hashCode, null);
        if (count > 0) {
            int elementPosition = Arrays.binarySearch(data, 0, count, elem);
            if (elementPosition >= 0) {
                return (V) getInList(elementPosition, key);
            }
        }
        return null;
    }

    public boolean delete(K key) {
        int hashCode = getHashCode(key);
        HashMapElement<K, V> elem = new HashMapElement<>(hashCode, null);
        int elementPosition = Arrays.binarySearch(data, 0, count, elem);
        if (elementPosition >= 0) {
            if (data[elementPosition].count > 0) {
                removeFromList(elementPosition, key);
            } else {
                System.arraycopy(data, elementPosition + 1, data, elementPosition, data.length - elementPosition - 1);
                data[count] = null;
                count--;
            }
        }
        return false;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }

    public static class HashMapElement<K, V> implements Comparable {
        int hashCode;
        int count = 0;
        Entry<K, V> entry;
        public HashMapElement(int hashCode, Entry<K, V> entry) {
            this.hashCode = hashCode;
            this.entry = entry;
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
