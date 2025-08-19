package com.demoqa.TestBase;

import com.demoqa.utils.LocatorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        String locatorFile = "clients_locators.properties";

        public static WebElement getShadowIcon(WebDriver driver, String locatorFile) throws InterruptedException {
            WebElement shadowDom = LocatorUtils.getElement(locatorFile, "shadow_modal_xpath")
                    .findElement(LocatorUtils.getLocator(locatorFile, "datetime_component")).getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "datetime_icon_item"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "datetime_icon")).getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "icon_inner"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "icon_svg"));

            Thread.sleep(500); // Optional
            return shadowDom;
        }

        public static WebElement getMonthSelect(WebDriver driver, String locatorFile) {
            return LocatorUtils.getElement(locatorFile, "datetime_component").getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "picker_internal"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "month_column")).getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "month_button"));
        }

        public static WebElement getYearSelect(WebDriver driver, String locatorFile) {
            return LocatorUtils.getElement(locatorFile, "datetime_component").getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "picker_internal"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "year_column")).getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "year_button"));
        }

    public static WebElement getCalenderClick(WebDriver driver, String locatorFile){

            return LocatorUtils.getElement(locatorFile, "datetime_component")
                    .getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "calendar_open_ion"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "calendar_open_label"));

    }
        public static WebElement getDateSelect(WebDriver driver, String locatorFile) {
            return LocatorUtils.getElement(locatorFile, "datetime_component").getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "calendar_month"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "calendar_grid"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "calendar_day_button"));
        }


        public static WebElement getDoneButton(WebDriver driver, String locatorFile) {
            return LocatorUtils.getElement(locatorFile, "datetime_component").getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locatorFile, "datetime_footer"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "datetime_action_buttons"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "datetime_buttons"))
                    .findElement(LocatorUtils.getLocator(locatorFile, "done_button"));
        }
    }


