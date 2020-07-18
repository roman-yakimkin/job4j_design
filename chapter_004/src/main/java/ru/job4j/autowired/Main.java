package ru.job4j.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.autowired");
        context.refresh();
        Vehicle vehicle = context.getBean(Vehicle.class);
        vehicle.output();
    }
}
