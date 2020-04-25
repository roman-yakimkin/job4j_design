package ru.job4j.heroes;

/**
 * Класс - состояние юнита
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.04.2020
 * @version 1.0
 */
public enum UnitState {

    /**
     * Юнит благословлен
     */
    BLESSED {
        public int priorityModifier(int priority) {
            return priority + 20;
        }
        public int damageModifier(int damage) {
            return (int) (damage * 1.5);
        }
    },
    /**
     * Юнит в нормальном состоянии
     */
    NORMAL {
        public int priorityModifier(int priority) {
            return priority;
        }
        public int damageModifier(int damage) {
            return damage;
        }
    },
    /**
     *  Юнит проклят
     */
    DAMNED {
        public int priorityModifier(int priority) {
            return priority;
        }
        public int damageModifier(int damage) {
            return (int) (damage / 1.5);
        }
    };
    public abstract int priorityModifier(int priority);
    public abstract int damageModifier(int damage);
}
