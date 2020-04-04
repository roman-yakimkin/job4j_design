package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportManagerTest {
    @Test
    public void testReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);

        ReportManager rm = new ReportManager(new ReportEngine(store, new DefaultReportGenerate()));
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(rm.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void testITReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportManager rm = new ReportManager(new ITReportEngine(store, new HTMLReportGenerate()));
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
        assertThat(rm.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void testHRReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 300);
        Employee worker3 = new Employee("Stephan", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportManager rm = new ReportManager(new HRReportEngine(store, new HRReportGenerate()));
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())

                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())

                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())

                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());

        assertThat(rm.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void testAccountingReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100.50);
        store.add(worker);
        ReportManager rm = new ReportManager(new AccountingReportEngine(store, new AccountingReportGenerate()));
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(AccountingReportGenerate.getFormattedSalary(worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(rm.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void testJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportManager rm = new ReportManager(new ReportEngine(store, new JSONReportGenerate()));
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
        assertThat(rm.generate(em -> true), is(expect.toString()));

    }

    @Test
    public void testXML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportManager rm = new ReportManager(new ReportEngine(store, new XMLReportGenerate()));
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
        assertThat(rm.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void testHTMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportManager rm = new ReportManager(new ReportEngine(store, new HTMLReportGenerate()));
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
        assertThat(rm.generate(em -> true), is(expect.toString()));
    }
}
