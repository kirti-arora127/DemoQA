package com.demoqa.TestRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@Listeners(StartReport.class)

@CucumberOptions(
        features = "src/test/resources/features/form.feature",
        glue = {"com.demoqa.StepDefinitions"},  // Use . instead of /
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/cucumberTestReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
       // tags="@smoke",
        monochrome = true,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

          // Execute the method

}

