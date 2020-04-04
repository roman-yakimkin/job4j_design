package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Генератор отчетов по умолчанию
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 04.04.2020
 * @version 1.0
 */
public class DefaultReportGenerate implements IReportGenerate {
    /**
     * Генерация отчета по умолчанию
     * @param filter - условие
     * @return - сгенерированный отчет в текстовом виде
     */
    public String generate(List<Employee> employeeList) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : employeeList) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
