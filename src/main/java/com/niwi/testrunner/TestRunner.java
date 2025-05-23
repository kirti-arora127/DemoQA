package com.niwi.testrunner;


import com.niwi.reporting.StartReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

//@Listeners(StartReport.class)
@CucumberOptions(
        features = "src/test/resources/features/settings_form.feature",
        glue = {"com.niwi.stepdefinition"},  // Use . instead of /
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/cucumberTestReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
     //   tags="@smoke1",
        monochrome = true,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

