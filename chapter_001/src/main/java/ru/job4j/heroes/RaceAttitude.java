package ru.job4j.heroes;

/**
 * Позиция расы
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 19.04.2020
 * @version 1.0
 */
public enum RaceAttitude {
    POSITIVE {
        public RaceAttitude getOpposite() {
            return NEGATIVE;
        }
    },
    NEGATIVE {
        public RaceAttitude getOpposite() {
            return POSITIVE;
        }
    }
}
