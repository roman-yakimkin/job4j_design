package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HTMLReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        IReportEngine engine = new HTMLReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<table>")
                .append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>")
                .append("<tr>")
                .append("<td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td>")
                .append("</tr>")
                .append("</table>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
