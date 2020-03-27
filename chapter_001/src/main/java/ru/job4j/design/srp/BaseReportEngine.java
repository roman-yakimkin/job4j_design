package ru.job4j.design.srp;

import java.util.function.Predicate;

abstract public class BaseReportEngine implements IReportEngine {
    private Store store;
    public BaseReportEngine(Store store) {
        this.store = store;
    }
    public Store getStore() {
        return store;
    }
    abstract public String generate(Predicate<Employee> filter);
}
