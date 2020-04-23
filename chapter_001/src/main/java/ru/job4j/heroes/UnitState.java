package ru.job4j.heroes;

import java.util.function.Function;

/**
 * Класс - состояние юнита
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public enum UnitState {
    BLESSED {
        public int priorityModifier(int priority) {
            return priority + 20;
        }
        public int damageModifier(int damage) {
            return (int)(damage * 1.5);
        }
    },
    NORMAL {
        public int priorityModifier(int priority) {
            return priority;
        }
        public int damageModifier(int damage) {
            return damage;
        }
    },
    DAMNED {
        public int priorityModifier(int priority) {
            return priority;
        }
        public int damageModifier(int damage) {
            return (int)(damage / 1.5);
        }
    };
    public abstract int priorityModifier(int priority);
    public abstract int damageModifier(int damage);
}
