package com.niwi.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.niwi.testbase.TestBase;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StartReport extends TestBase{

    private static ExtentSparkReporter extentSparkReporter;
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/Report_" + timestamp + ".html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);

            // Configurations
            extentSparkReporter.config().setDocumentTitle("Automation Report");
            extentSparkReporter.config().setReportName("Test Report");
            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        }
        return extentReports;
    }
    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
    public static void createTest(String scenarioName) {
        extentTest = getInstance().createTest(scenarioName);
    }
    public static ExtentTest getTest() {
        return extentTest;
    }
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            driver = TestBase.getDriver();
            String screenshotPath = Hooks.takeScreenshot(driver, scenario.getName());
            StartReport.getTest().fail("Step failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
//        else {
//            StartReport.getTest().log(Status.PASS, "Step passed: " + scenario.getName());
//        }
    }

}
