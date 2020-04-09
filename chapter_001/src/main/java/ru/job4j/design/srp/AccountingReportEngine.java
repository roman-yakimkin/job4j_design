package ru.job4j.design.srp;

import java.io.PrintStream;
import java.util.function.Predicate;

public class AccountingReportEngine extends BaseReportEngine {
    public AccountingReportEngine(Store store, IReportGenerate reportGenerate) {
        super(store, reportGenerate);
    }
}
