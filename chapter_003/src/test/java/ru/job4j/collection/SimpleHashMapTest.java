package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    public static class KeyEntity {
        int id;
        String name;

        public KeyEntity(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof KeyEntity)) {
                return false;
            }
            KeyEntity keyEntity = (KeyEntity) o;
            return id == keyEntity.id && Objects.equals(name, keyEntity.name);
        }

        @Override
        public int hashCode() {
            return id * 10;
        }
    }

    @Test
    public void addAndGetThreePairs() {
        KeyEntity key1 = new KeyEntity(1, "first");
        KeyEntity key2 = new KeyEntity(2, "second");
        KeyEntity key3 = new KeyEntity(3, "third");

        SimpleHashMap<KeyEntity, String> map = new SimpleHashMap<>();
        map.put(key1, "first value");
        map.put(key2, "second value");
        map.put(key3, "third value");

        assertThat(map.get(key1), is("first value"));
        assertThat(map.get(key2), is("second value"));
        assertThat(map.get(key3), is("third value"));
    }

    @Test
    public void addAndGetFourPairWithCollision() {
        KeyEntity key1 = new KeyEntity(1, "first");
        KeyEntity key2 = new KeyEntity(2, "second");
        KeyEntity key3 = new KeyEntity(3, "third");
        KeyEntity key21 = new KeyEntity(2, "second with collision");

        SimpleHashMap<KeyEntity, String> map = new SimpleHashMap<>();
        map.put(key1, "first value");
        map.put(key2, "second value");
        map.put(key3, "third value");
        map.put(key21, "second value with collision");

        assertThat(map.get(key1), is("first value"));
        assertThat(map.get(key2), is("second value"));
        assertThat(map.get(key3), is("third value"));
        assertThat(map.get(key21), is("second value with collision"));
    }

    @Test
    public void addAndRemovePair() {
        KeyEntity key1 = new KeyEntity(1, "first");
        SimpleHashMap<KeyEntity, String> map = new SimpleHashMap<>();
        map.put(key1, "first value");
        assertThat(map.get(key1), is("first value"));
        map.delete(key1);
        assertThat(map.get(key1), is(nullValue()));
    }

    @Test
    public void addAndRemovePairWithCollision() {
        KeyEntity key11 = new KeyEntity(1, "first");
        KeyEntity key12 = new KeyEntity(1, "first with collision");
        SimpleHashMap<KeyEntity, String> map = new SimpleHashMap<>();
        map.put(key11, "first value");
        map.put(key12, "first value with collision");
        assertThat(map.get(key11), is("first value"));
        assertThat(map.get(key12), is("first value with collision"));
        map.delete(key12);
        assertThat(map.get(key11), is("first value"));
        assertThat(map.get(key12), is(nullValue()));
    }
}