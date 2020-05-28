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
        char varChar = 'A';
        int varInt = 1000;
        long varLong = 999999999999999999L;
        byte varByte = -123;
        boolean varBoolean = true;
        short varShort = 12345;
        float varFloat = 123456.78F;
        double varDouble = 123456789012.34;
        LOG.debug("There art some variables of different types: {}, {}, {}, {}, {}, {}, {}, {}", varChar, varInt, varLong, varByte, varBoolean, varShort, varFloat, varDouble);
    }
}
