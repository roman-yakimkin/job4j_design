package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Менеджер отчетов, работающий с отчетом любого подразделения
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 26.03.2020
 * @version 1.0
 */
public class ReportManager {
    IReportEngine reportEngine;

    public ReportManager(BaseReportEngine reportEngine) {
        this.reportEngine = reportEngine;
    }

    public String generate(Predicate<Employee> filter) {
        return reportEngine.generate(filter);
    }
}
