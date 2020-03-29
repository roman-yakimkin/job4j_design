package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class XMLReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        IReportEngine engine = new XMLReportEngine(store);
        StringBuilder expect = new StringBuilder()
            .append("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>").append(System.lineSeparator())
                .append("<employees>")
                .append(System.lineSeparator())
                .append("   <employee>").append(System.lineSeparator())
                .append("      <name>").append(System.lineSeparator())
                .append("          ").append(worker.getName()).append(System.lineSeparator())
                .append("      </name>").append(System.lineSeparator())
                .append("      <hired>").append(System.lineSeparator())
                .append("          ").append(worker.getHired()).append(System.lineSeparator())
                .append("      </hired>").append(System.lineSeparator())
                .append("      <fired>").append(System.lineSeparator())
                .append("          ").append(worker.getFired()).append(System.lineSeparator())
                .append("      </fired>").append(System.lineSeparator())
                .append("      <salary>").append(System.lineSeparator())
                .append("          ").append(worker.getSalary()).append(System.lineSeparator())
                .append("      </salary>").append(System.lineSeparator())
                .append("   </employee>").append(System.lineSeparator())
                .append("</employees>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}
