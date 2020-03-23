package ru.job4j.template;

import java.util.List;

public class SuperfluousInArgsException extends Exception {
    List<String> keys;

    public List<String> getKeys(){
        return keys;
    }

    public SuperfluousInArgsException(String message, List<String> keys) {
        super(message);
        this.keys = keys;
    }

}
