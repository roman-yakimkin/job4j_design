package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс - сервис для рассылки почты
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 07.06.2020
 * @version 1.0
 */
public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        String subject = String.format("Notification %s to to email %s", user.userName, user.email);
        String body = String.format("Add a new event to %s", user.userName);
        pool.submit(() -> send(subject, body, user.email));
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class User {
        private final String userName;
        private final String email;

        public User(String userName, String email) {
            this.userName = userName;
            this.email = email;
        }
    }
}
