package com.demoqa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LocatorUtils {
    static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public LocatorUtils(WebDriver driver) {
        LocatorUtils.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static By getLocator(String filename, String propertykey) {
        String values = ReadFiles.getDataFromPropertyFile(filename, propertykey);
        String[] locatorParts = values.split(":", 2);
        String locatorType = locatorParts[0].trim();
        String locatorValue = locatorParts[1].trim();
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "class":
                return By.className(locatorValue);
            case "tag":
                return By.tagName(locatorValue);
            case "linktext":
                return By.linkText(locatorValue);
            case "partiallinktext":
                return By.partialLinkText(locatorValue);
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }



    public static WebElement getElement(String filename, String propertykey) {
        return driver.findElement(getLocator(filename, propertykey));
    }
  public static WebElement getElement(WebElement element,String filename, String propertykey) {
       return element.findElement(getLocator(filename, propertykey));
   }//this is used when we want to do section or form indexing

    public static List<WebElement> getElements(String filename,String propertykey){

        return driver.findElements(getLocator(filename,propertykey));
    }
}
