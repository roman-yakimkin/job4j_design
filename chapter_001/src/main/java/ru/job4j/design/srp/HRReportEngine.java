package ru.job4j.design.srp;

import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Генератор отчетов для HR службы
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 21.03.2020
 * @version 1.0
 */
public class HRReportEngine extends ReportEngine {
    public HRReportEngine(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : getStore()
                .findBy(filter)
                .stream()
                .sorted((e1, e2) -> ((int)(e2.getSalary() - e1.getSalary())))
                .collect(Collectors.toList())) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
