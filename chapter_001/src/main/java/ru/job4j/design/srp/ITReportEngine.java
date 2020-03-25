package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Генератор отчетов для IT - службы
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 21.03.2020
 * @version 1.0
 */
public class ITReportEngine extends BaseReportEngine implements IReportEngine {

    public ITReportEngine(Store store) {
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
