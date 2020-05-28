package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс для демонстрации использования логгера для вывода исключений
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 28.05.2020
 * @version 1.0
 */
public class UsageLog4jAndExceptions {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4jAndExceptions.class.getName());

    public static void main(String[] args) {
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
