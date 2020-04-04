package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HRReportGenerate implements IReportGenerate {
    @Override
    public String generate(List<Employee> employeeList) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : employeeList
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
