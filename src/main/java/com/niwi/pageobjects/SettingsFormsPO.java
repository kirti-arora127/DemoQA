package com.niwi.pageobjects;

import com.niwi.utils.Constants;
import com.niwi.utils.LocatorUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class SettingsFormsPO {
    String locator_file = "settings_locators.properties";
  //  String data_file="creatediet_data.properties";

    WebDriver driver;
    public SettingsFormsPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
public void settingsTab(){
    try{

       WebElement sidemenudown = LocatorUtils.getElement(locator_file, "sidemenudown");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sidemenudown);
        sidemenudown.click();
        WebElement settings = LocatorUtils.getElement(locator_file, "Settings_tab");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", settings);
       Thread.sleep(500);
        Constants.doClick(settings);
        WebElement account = LocatorUtils.getElement(locator_file, "account_tab");
        Constants.doClick(account);
        WebElement Forms = LocatorUtils.getElement(locator_file, "form_tab");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Forms);
        Thread.sleep(500);
        Constants.doClick(Forms);
        WebElement health_progress_form = LocatorUtils.getElement(locator_file, "health_progress_form");

        Constants.doClick(health_progress_form);
        WebElement add_new_section_2nd_place = LocatorUtils.getElement(locator_file, "add_new_section_2nd_place");
        Constants.doClick(add_new_section_2nd_place);

        Thread.sleep(2000);
    } catch (RuntimeException | InterruptedException e) {
        throw new RuntimeException(e);
    }


}
public void health_progress(){
    try {

 //       WebElement full_form_xpath = LocatorUtils.getElement(locator_file, "full_form_xpath");
   //     Constants.doClick(full_form_xpath);
     //   WebElement first_section_edit = LocatorUtils.getElement(locator_file, "first_section_edit");
       // Constants.doClick(first_section_edit);
        WebElement enter_section_name = LocatorUtils.getElement(locator_file, "enter_section_name");
        enter_section_name.sendKeys("Diet Preferences");
        WebElement enter_field_name = LocatorUtils.getElement(locator_file, "enter_field_name");
        enter_field_name.sendKeys("Appetite in Morning");
        Thread.sleep(500);
       // WebElement select_field = LocatorUtils.getElement(locator_file, "select_field");
        //select_field.click();
        Select select = new Select(LocatorUtils.getElement(locator_file,"select_field"));
        // Or by index
        select.selectByVisibleText("Single Select");

        WebElement select_option1 = LocatorUtils.getElement(locator_file, "select_option1");
        select_option1.sendKeys("Option 1");
        WebElement select_option2 = LocatorUtils.getElement(locator_file, "select_option2");
        select_option2.sendKeys("Option 2");
        //  WebElement enter_des = LocatorUtils.getElement(locator_file, "enter_des");
        //enter_des.sendKeys("Select from dropdown");
        WebElement Add_new_field = LocatorUtils.getElement(locator_file, "Add_new_field");
        Add_new_field.click();
        WebElement enter_second_field_name = LocatorUtils.getElement(locator_file, "enter_second_field_name");
        enter_second_field_name.sendKeys("Appetite in Afternoon");

        // WebElement mandat_check1 = LocatorUtils.getElement(locator_file, "mandat_check1");
        //Constants.doClick(mandat_check1);
        WebElement update_button = LocatorUtils.getElement(locator_file, "update_button");
        Constants.doClick(update_button);

        Thread.sleep(2000);
        //WebElement deletebutton = LocatorUtils.getElement(locator_file, "deletebutton");
        //Constants.doClick(deletebutton);


    } catch (RuntimeException | InterruptedException e) {
        throw new RuntimeException(e);
    }
}
public void savehealthprogress() throws InterruptedException {
    WebElement savebutton = LocatorUtils.getElement(locator_file, "savebutton");
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", savebutton);
    Thread.sleep(500);
    Constants.doClick(savebutton);


}
public void userClicksOnClientTabAngular(){
    WebElement Clienttabangular = LocatorUtils.getElement(locator_file, "Clienttabangular");
    Clienttabangular.click();

    List<WebElement> elements = LocatorUtils.getElements(locator_file,"clientdetailstab");
    if (!elements.isEmpty()) {
        elements.get(0).click();
    } else {
        LocatorUtils.getElement(locator_file,"clientdetailstab2").click();
    }



}
}
