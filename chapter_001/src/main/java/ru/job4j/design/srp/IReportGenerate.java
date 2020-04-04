package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public interface IReportGenerate {
    public String generate(List<Employee> employees);
}
