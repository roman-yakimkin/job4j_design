package ru.job4j.design.srp;

import java.util.function.Predicate;

public interface IReportEngine {
    public Store getStore();
    public void setStore(Store store);
    public String generate(Predicate<Employee> filter);
}
