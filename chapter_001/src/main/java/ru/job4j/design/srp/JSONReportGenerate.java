package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Генерация отчета в JSON формате
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 04.04.2020
 * @version 1.0
 */
public class JSONReportGenerate implements IReportGenerate {
    @Override
    public String generate(List<Employee> employeeList) {
        StringBuilder text = new StringBuilder();
        text.append("{")
                .append(System.lineSeparator());
        for (Employee employee : employeeList) {
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
