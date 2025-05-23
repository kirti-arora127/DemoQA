package com.niwi.pageobjects;

import com.niwi.utils.Constants;
import com.niwi.utils.LocatorUtils;
import com.niwi.utils.ReadFiles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPO {
    WebDriver driver;
    String locator_file="login_locators.properties";
    String data_file="Data.properties";

    public LoginPO(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void enter_username(){
        String username_value= ReadFiles.getDataFromPropertyFile(data_file,"username");
        WebElement username=LocatorUtils.getElement(locator_file,"username");
        Constants.enterValues(username,username_value);

    }

    public void enter_password(){
        String password_value=ReadFiles.getDataFromPropertyFile(data_file,"password");
        WebElement password=LocatorUtils.getElement(locator_file,"password");
        Constants.enterValues(password,password_value);
    }

    public void clickOnLogin(){
        WebElement login_btn=LocatorUtils.getElement(locator_file,"loginbutton");
        Constants.doClick(login_btn);
    }

//    public void validateUserLOggedInSuccessfully() throws InterruptedException{
//        //Assert.assertEquals(driver.getCurrentUrl().contains("dashboard"),true);
//        System.out.println("Login");
//
//    }
}
