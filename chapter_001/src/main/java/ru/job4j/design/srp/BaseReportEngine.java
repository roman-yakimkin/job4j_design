package ru.job4j.design.srp;

import java.util.function.Predicate;

public class BaseReportEngine {
    private Store store;
    public BaseReportEngine(Store store) {
        this.store = store;
    }
    public Store getStore() {
        return store;
    }
}
