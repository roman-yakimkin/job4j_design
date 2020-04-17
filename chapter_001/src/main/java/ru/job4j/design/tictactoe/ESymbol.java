package ru.job4j.design.tictactoe;

/**
 * Символы для игры "крестики - нолики"
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 05.04.2020
 */
public enum ESymbol implements Symbol {
    X_SYMBOL {
        @Override
        public void display() {
            System.out.print(" X ");
        }

        @Override
        public String toString() {
            return "X";
        }
    },
    O_SYMBOL {
        @Override
        public void display() {
            System.out.print(" O ");
        }

        @Override
        public String toString() {
            return "O";
        }
    },
    EMPTY_SYMBOL {
        @Override
        public void display() {
            System.out.print(" . ");
        }
        @Override
        public String toString() {
            return ".";
        }

    };
    @Override
    public Symbol get() {
        return this;
    }
}
