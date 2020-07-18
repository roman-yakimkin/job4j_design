package ru.job4j.autowired;

import org.springframework.stereotype.Component;

/**
 * Class - Engine for demonsttration of autowiring
 * @author Roman Yakimkin (r.yakmkin@yandex.ru)
 * @since 18.05.2020
 * @version 1.0
 */
@Component
public class Engine {
    private String name = "Carburetor";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
