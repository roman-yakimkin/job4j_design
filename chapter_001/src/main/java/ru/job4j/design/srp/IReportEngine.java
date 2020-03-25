package ru.job4j.design.srp;

import java.util.function.Predicate;

public interface IReportEngine {
    public String generate(Predicate<Employee> filter);
}
