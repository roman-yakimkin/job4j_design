package ru.job4j.gc;

/**
 * Класс для демонстрации работы сборщика мусора
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 22.05.2020
 * @version 1.0
 */
public class MemoryUsage {

    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
//            System.out.println("User " + this + " has been destroyed");
        }

        @Override
        public String toString() {
            return "User{name=" + name + "}";
        }
    }

    public static void main(String[] args) {
        System.out.println("start ...");
        User user;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 1000; j++) {
                user = new User("Test user #" + i);
                user = null;
            }
            System.gc();
            info("Iteration " + (i + 1));
        }
        info("Before garbage collection");
        System.gc();
        info("After garbage collection");
        user = new User("The latest user");
        info("After creating of the latest user");
        System.out.println("finish ...");
    }

    public static void info(String msg) {
        int koef = 1;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Heap utilization statistics (bytes) " + msg);
//        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / koef);
        System.out.println("Free memory: " + runtime.freeMemory() / koef);
//        System.out.println("Total memory: " + runtime.totalMemory() / koef);
//       System.out.println("Max memory: " + runtime.maxMemory() / koef);
    }
}
