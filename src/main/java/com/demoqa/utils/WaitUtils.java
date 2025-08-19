package com.demoqa.utils;

import com.demoqa.TestBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils extends TestBase {
   static  WebDriverWait wait;
    public static void implicitWait()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
    }

    public static WebDriverWait getWait(WebDriver driver,int timeout){
        return new WebDriverWait(driver,Duration.ofSeconds(timeout));
    }
}
