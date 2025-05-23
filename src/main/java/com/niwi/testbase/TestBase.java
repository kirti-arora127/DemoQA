package com.niwi.testbase;

import com.niwi.utils.LocatorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
   public static WebDriver driver;



    public static void openBrowser(){
        try{
            if (driver == null) {  // ✅ Prevents multiple browser instances
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                LocatorUtils.setDriver(driver);  // ✅ Ensure LocatorUtils has the driver
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void closeBrowser(){
        try{
            driver.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            openBrowser();  // ✅ Ensures driver is initialized if not set
        }
        return TestBase.driver;
    }
}
