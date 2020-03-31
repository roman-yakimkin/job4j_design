package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Генератор отчета для формата XML
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 29.03.2020
 * @version 1.0
 */
public class XMLReportEngine implements IReportEngine {
    Store store;

    public XMLReportEngine(Store store) {
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
        text.append("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>").append(System.lineSeparator())
            .append("<employees>")
                .append(System.lineSeparator());
        for (Employee employee : getStore().findBy(filter)) {
            text.append("   <employee>").append(System.lineSeparator())
                    .append("      <name>").append(System.lineSeparator())
                    .append("          ").append(employee.getName()).append(System.lineSeparator())
                    .append("      </name>").append(System.lineSeparator())
                    .append("      <hired>").append(System.lineSeparator())
                    .append("          ").append(employee.getHired()).append(System.lineSeparator())
                    .append("      </hired>").append(System.lineSeparator())
                    .append("      <fired>").append(System.lineSeparator())
                    .append("          ").append(employee.getFired()).append(System.lineSeparator())
                    .append("      </fired>").append(System.lineSeparator())
                    .append("      <salary>").append(System.lineSeparator())
                    .append("          ").append(employee.getSalary()).append(System.lineSeparator())
                    .append("      </salary>").append(System.lineSeparator())
                    .append("   </employee>").append(System.lineSeparator());
        }
        text.append("</employees>");
        return text.toString();
    }
}
