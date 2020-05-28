package io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Демонстрация использования логов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 17.05.2020
 * @version 1.0
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
