package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Генератор отчетов для HR службы
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 21.03.2020
 * @version 1.0
 */
public class HRReportEngine extends BaseReportEngine {
    public HRReportEngine(Store store, IReportGenerate reportGenerate) {
        super(store, reportGenerate);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employeeList = getStore()
                .findBy(filter)
                .stream()
                .sorted((e1, e2) -> ((int)(e2.getSalary() - e1.getSalary())))
                .collect(Collectors.toList());
        return getReportGenerate().generate(employeeList);
    }
}
