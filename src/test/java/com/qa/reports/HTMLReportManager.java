package com.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HTMLReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports reports;
    public ExtentTest test;
    public String repName;

    public String timeStamp;


    public void initializeSparkReporter() {
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "test-report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter("reports/html/" + repName);
        sparkReporter.config().setDocumentTitle("QA - test project");
        sparkReporter.config().setReportName("");
        sparkReporter.config().setTheme(Theme.DARK);

    }

    public void onStart(ITestContext testContext) {
        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Application", "");
        reports.setSystemInfo("Operating System", System.getProperty("os.name"));
        reports.setSystemInfo("User Name", System.getProperty("user.name"));
        reports.setSystemInfo("Environment", "QA environment");
    }

    public void onTestSuccess(ITestResult result) {

        test = reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {

        test = reports.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());

    }

    public void onTestSkipped(ITestResult result) {

        test = reports.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());

    }

    public void onFinish(ITestContext testContext) {
        reports.flush();
    }
}



