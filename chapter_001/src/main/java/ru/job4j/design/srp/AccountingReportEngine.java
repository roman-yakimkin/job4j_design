package ru.job4j.design.srp;

import java.io.PrintStream;
import java.util.function.Predicate;

public class AccountingReportEngine extends ReportEngine {
    public AccountingReportEngine(Store store) {
        super(store);
    }

    /**
     * Получить зарплату в отформатированном виде
     * @param salary - исходная зарплата
     * @return - зарплата в отформатированном виде
     */
    public String getFormattedSalary(double salary) {
        String[] values = String.valueOf(salary).split("\\.");
        String roubles = values[0];
        String kopecks = (values.length == 2) ? values[1] : "00";
        return String.format("%s руб. %s коп.", roubles, kopecks);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : getStore().findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(getFormattedSalary(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();

    }
}
