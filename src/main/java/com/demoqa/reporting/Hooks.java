package com.demoqa.reporting;


import com.demoqa.PageObject.RegistrationPO;

import com.demoqa.TestBase.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks{
    public static WebDriver driver;

     RegistrationPO registrationPO;
     @Before(order=0)
     public void loginOnce(Scenario scenario){
         StartReport.createTest(scenario.getName());
         if (TestBase.getDriver() == null) {  // ✅ Ensure WebDriver is initialized
             TestBase.openBrowser();
         }
         driver=TestBase.getDriver();
         registrationPO=new RegistrationPO(driver);

     }

//     @AfterAll
//    public void tearDown(Scenario scenario){
//         driver=TestBase.getDriver();
//         if(scenario.isFailed()){
//            String screenshot_path= takeScreenshot(driver, scenario.getName());
//         }
//         TestBase.closeBrowser();
//        // isLoggedIn=false;
//         StartReport.flushReports();
//     }


    @After(order = 1)
    public void tearDown(Scenario scenario) {  // Keep your tearDown name
        driver = TestBase.getDriver();
        if (scenario.isFailed()) {
            String screenshot_path = takeScreenshot(driver, scenario.getName());
        }
        TestBase.closeBrowser();  // Close the browser after each scenario
        StartReport.flushReports();  // Flush the report after each scenario

        // Apply fixed wait between scenarios (using Thread.sleep for example)
        try {
            Thread.sleep(5000); // Wait for 5 seconds between scenarios
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String takeScreenshot(WebDriver driver,String methodName) {
        // Highlight the element first
        String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File screenshotDirectory = new File(screenshotDir);
        //String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + methodName + "_" + timestamp + ".png";
        // Create the screenshots directory if it doesn't exist
        if (!screenshotDirectory.exists()) {
            screenshotDirectory.mkdirs();
        }
        // Full path of the screenshot file
        String screenshotPath = screenshotDir + methodName + "_" + timestamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the relative path to the report for it to locate the file correctly
        return "./screenshots/" + methodName + "_" + timestamp + ".png";
    }
}
