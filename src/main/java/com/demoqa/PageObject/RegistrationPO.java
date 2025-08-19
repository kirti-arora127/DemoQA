package com.demoqa.PageObject;

import com.demoqa.utils.Constants;
import com.demoqa.utils.LocatorUtils;
import com.demoqa.utils.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPO {

    WebDriver driver;
    String locator_file="registration_Locators.properties";
    String data_file="Data.properties";

    public RegistrationPO(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

public void OpenTheRegistrationFormPage(){
    try {

        WebElement ClickForm = LocatorUtils.getElement(locator_file, "ClickForm");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", ClickForm);
        Thread.sleep(500);
        Constants.doClick(ClickForm);
        WebElement ClickPracticeForm= LocatorUtils.getElement(locator_file,"ClickPracticeForm");
        Constants.doClick(ClickPracticeForm);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void fill_form(){
    WebElement first_name= LocatorUtils.getElement(locator_file,"first_name");
    Constants.enterValues(first_name,"Jack");

    WebElement last_name= LocatorUtils.getElement(locator_file,"last_name");
    Constants.enterValues(last_name,"Danielle");

    WebElement UserEmail= LocatorUtils.getElement(locator_file,"UserEmail");
    Constants.enterValues(UserEmail,"email34@mailinator.com");

    WebElement gender= LocatorUtils.getElement(locator_file,"Female");
    Constants.doClick(gender);

    WebElement mobile= LocatorUtils.getElement(locator_file,"mobile");
    Constants.enterValues(mobile,"887654456");
    WebElement subjectInput = LocatorUtils.getElement(locator_file,"subject");
    String[] subjects = {"Computer Science", "Maths"};
    for (String s : subjects) {
        subjectInput.sendKeys(s);
        subjectInput.sendKeys(Keys.ENTER);
    }
    WebElement address= LocatorUtils.getElement(locator_file,"address");
    Constants.enterValues(address,"H. NO. 45, GANGA NAGAR");

}
}
