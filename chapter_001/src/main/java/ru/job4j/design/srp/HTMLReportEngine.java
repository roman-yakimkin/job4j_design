package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Генератор отчета в формате HTML
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 29.03.2020
 */
public class HTMLReportEngine extends BaseReportEngine {
    public HTMLReportEngine(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<table>");
        text.append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>");
        for (Employee employee : getStore().findBy(filter)) {
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
