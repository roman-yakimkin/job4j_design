package ru.job4j.design.srp;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class BaseReportEngine implements IReportEngine {
    private Store store;
    private IReportGenerate reportGenerate;
    public BaseReportEngine(Store store, IReportGenerate reportGenerate) {
        this.store = store;
        this.reportGenerate = reportGenerate;
    }

    @Override
    public Store getStore() {
        return store;
    }

    @Override
    public void setStore(Store store) {
        this.store = store;
    }

    public IReportGenerate getReportGenerate() {
        return reportGenerate;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return reportGenerate.generate(store.findBy(filter));
    };
}
