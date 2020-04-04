package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class XMLReportGenerate implements IReportGenerate {
    @Override
    public String generate(List<Employee> employeeList) {
        StringBuilder text = new StringBuilder();
        text.append("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>").append(System.lineSeparator())
                .append("<employees>")
                .append(System.lineSeparator());
        for (Employee employee : employeeList) {
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
