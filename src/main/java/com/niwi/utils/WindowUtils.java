package com.niwi.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Set;

public class WindowUtils {


    public static void switchToWindow(WebDriver driver, int tabindex){
        try {
            Set<String> allwindowids = driver.getWindowHandles();
            ArrayList<String> tabs = new ArrayList<>(allwindowids);
            if (tabindex < tabs.size()) {
                driver.switchTo().window(tabs.get(tabindex));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
