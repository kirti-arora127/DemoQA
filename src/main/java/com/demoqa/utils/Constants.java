package com.demoqa.utils;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;

import java.util.Date;

public class Constants {
    public static Faker faker;
    public static void doClick(WebElement element){
        try{
            element.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
   /* public static void clickElement(String locator_file,String locatorKey) {
        WebElement element = LocatorUtils.getElement(locator_file, locatorKey);
        Constants.doClick(element);
    }

    public static void enterValue(String locator_file,String locatorKey, String value) {
        WebElement element = LocatorUtils.getElement(locator_file, locatorKey);
        Constants.enterValues(element, value);
    }*/

    public static String fillAndReturn(WebElement element, String dataType) {

            String data = enterDynamicData(dataType);
            enterValues(element, data);
            return data;
    }
    public static void enterValues(WebElement element,String value){
        try{
            element.sendKeys(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String enterDynamicData(String typeofdata){
        String value=null;
        int value2 = 0;
        Date value3 = null;


        faker = new Faker();
        switch (typeofdata){
            case "index":
                value2 = faker.number().numberBetween(0, 10); // random index between 0 and 4
                System.out.println("Random index: " + value2);
                break;
            case "name":
                value= faker.name().fullName();
                break;
            case "number":
                value= faker.number().digits(2);
                break;
            case "description":
                value=faker.commerce().department();
                break;
            case "email_id":
                value=faker.internet().emailAddress();
                break;
            case "phone_no":
                value=faker.number().digits(10);
                break;
            case "code":
                value=faker.name().title();
                break;
            case "random sentence":
                value = faker.lorem().sentence(4);
                break;
            case "calendar":
                value3 = faker.date().birthday();
                break;
            default:
                value="Invalid";
        }
        return value;
    }
}
