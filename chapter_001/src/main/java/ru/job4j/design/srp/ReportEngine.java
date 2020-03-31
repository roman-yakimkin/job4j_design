package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Система отчетов по сотрудниками
 */
public class ReportEngine implements IReportEngine {
    Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Генерация отчета по умолчанию
     * @param filter - условие
     * @return - сгенерированный отчет в текстовом виде
     */
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
            .append(System.lineSeparator());
        for (Employee employee : getStore().findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
