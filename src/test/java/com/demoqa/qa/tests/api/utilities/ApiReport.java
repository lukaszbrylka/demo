package com.demoqa.qa.tests.api.utilities;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.reports.HTMLReportManager;
import org.testng.ITestContext;

public class ApiReport extends HTMLReportManager {
    @Override
    public void initializeSparkReporter() {
        super.initializeSparkReporter();
        repName = "api-test-report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter("reports/html/api/" + repName);
        sparkReporter.config().setDocumentTitle("QA - test project");
        sparkReporter.config().setReportName("API ");
        sparkReporter.config().setTheme(Theme.DARK);
    }

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        initializeSparkReporter();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Application", "Book Store Web API");
    }
}
