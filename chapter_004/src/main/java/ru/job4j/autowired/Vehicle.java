package ru.job4j.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class - Vehicle for demonstration of autowiring
 * @author Roman Yakimin (r.yakimkin@yandex.ru)
 * @since 18.07.2020
 * @version 1.0
 */
@Component
public class Vehicle {
    private String name;

    @Autowired
    private Engine engine;

    public void output() {
        System.out.println(name + ", engine = " + engine.getName());
    }
}
