package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JSONReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        IReportEngine engine = new JSONReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("{")
                .append(System.lineSeparator())
                .append("   {")
                .append(System.lineSeparator()).append("      \"name\": \"").append(worker.getName()).append("\"")
                .append(System.lineSeparator()).append("      \"hired\": \"").append(worker.getHired()).append("\"")
                .append(System.lineSeparator()).append("      \"fired\": \"").append(worker.getFired()).append("\"")
                .append(System.lineSeparator()).append("      \"salary\": \"").append(worker.getSalary()).append("\"")
                .append(System.lineSeparator())
                .append("   }").append(System.lineSeparator())
                .append("}");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}
