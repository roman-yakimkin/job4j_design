package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Генерация отчета в формате HTML
 * @author Roman Yakimkin
 * @since 04.04.2020
 * @version 1.0
 */
public class HTMLReportGenerate implements IReportGenerate {
    @Override
    public String generate(List<Employee> employeeList) {
        StringBuilder text = new StringBuilder();
        text.append("<table>");
        text.append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>");
        for (Employee employee : employeeList) {
            text.append("<tr>")
                    .append("<td>").append(employee.getName()).append("</td>")
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td>")
                    .append("</tr>");
        }
        text.append("</table>");
        return text.toString();
    }

}
