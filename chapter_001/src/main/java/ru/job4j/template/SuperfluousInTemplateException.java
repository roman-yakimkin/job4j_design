package ru.job4j.template;

import java.util.List;

/**
 * Исключение "В исходной строке есть излишний шаблон для замены"
 */
public class SuperfluousInTemplateException extends Exception {
    List<String> substitutions;

    public List<String> getSubstitutions(){
        return substitutions;
    }

    public SuperfluousInTemplateException(String message, List<String> substitutions) {
        super(message);
        this.substitutions = substitutions;
    }
}
