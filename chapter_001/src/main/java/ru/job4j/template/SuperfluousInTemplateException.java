package ru.job4j.template;

import java.util.List;

/**
 * Исключение "В исходной строке есть излишний шаблон для замены"
 */
public class SuperfluousInTemplateException extends Exception {
    public SuperfluousInTemplateException(String message) {
        super(message);
    }
}
