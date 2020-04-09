package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Система отчетов по сотрудниками
 */
public class ReportEngine implements IReportEngine {
    private Store store;
    private IReportGenerate reportGenerate;
    public ReportEngine(Store store, IReportGenerate reportGenerate) {
        this.store = store;
        this.reportGenerate = reportGenerate;
    }
    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }
    public IReportGenerate getReportGenerate() {
        return reportGenerate;
    }
    public String generate(Predicate<Employee> filter) {
        return reportGenerate.generate(store.findBy(filter));
    }
}
