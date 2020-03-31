package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Генератор отчета в формате JSON
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 29.03.2020
 * @version 1.0
 */
public class JSONReportEngine implements IReportEngine {
    Store store;

    public JSONReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("{")
                .append(System.lineSeparator());
        for (Employee employee : getStore().findBy(filter)) {
            text.append("   {").append(System.lineSeparator())
                    .append("      \"name\": ").append("\"").append(employee.getName()).append("\"").append(System.lineSeparator())
                    .append("      \"hired\": ").append("\"").append(employee.getHired()).append("\"").append(System.lineSeparator())
                    .append("      \"fired\": ").append("\"").append(employee.getFired()).append("\"").append(System.lineSeparator())
                    .append("      \"salary\": ").append("\"").append(employee.getSalary()).append("\"").append(System.lineSeparator())
                .append("   }").append(System.lineSeparator());
        }
        text.append("}");
        return text.toString();
    }
}
