package com.niwi.pageobjects;

import com.niwi.utils.Constants;
import com.niwi.utils.LocatorUtils;
import com.niwi.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.JavaBean;
import java.lang.constant.Constable;
import java.sql.DriverManager;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class CalendarPO {
    String locator_file="calendar_locators.properties";
    WebDriver driver;

    public CalendarPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void click_calendar_tab(){
        try{
            WebElement calendar_tab= LocatorUtils.getElement(locator_file,"calendar_tab");
            Constants.doClick(calendar_tab);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void book_appointment(){
        try{
            System.out.println("Visiting Appointment");
            appointment_for_client("Dr. Elida Hegmann (2025022710456)","Visit","May","8","13","I want to discuss for weight loss");
            Thread.sleep(2000);
//            System.out.println("Whatsapp Appointment");
//            appointment_for_client("Dr. Glennis Wyman (2025031210640)","Whatsapp","May","21","12","I want to discuss for weight loss");
//            Thread.sleep(2000);
//            System.out.println("Phone Call Appointment");
//            appointment_for_client("Dr. Ivan Stanton (2025032410804)","Phone Call","April","17","16","I want to discuss for weight loss");
//            Thread.sleep(2000);
//            System.out.println("SMS Appointment");
//            appointment_for_client("Dr. Brandee Crooks (2025030610542)","SMS","May","19","19","I want to discuss for weight loss");
//            Thread.sleep(2000);
//            System.out.println("Email Appointment");
//            appointment_for_client("Guadalupe Hansen (2025030710570)","Email","May","12","13","I want to discuss for weight loss");
//            Thread.sleep(2000);
//            System.out.println("Video Call Appointment");
//            appointment_for_client("Harrison Macejkovic (2025022810483)","Video Call","April","22","14","I want to discuss for weight loss");
//            Thread.sleep(2000);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void appointment_for_client(String clientName, String meetingMode, String month,String meeting_date, String time, String descriptionText) {
        try {

            WebElement appointment_btn = LocatorUtils.getElement(locator_file, "appointment_btn");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", appointment_btn);

            // Select Client
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-message")));
            WebElement select_client_dd = LocatorUtils.getElement(locator_file, "select_client_dropdown");
            Constants.doClick(select_client_dd);
            String client_locator_template = LocatorUtils.getLocator(locator_file, "select_client_option").toString();
          client_locator_template = client_locator_template.replace("By.xpath: ", "");
            String client_dynamic_locator = client_locator_template.replace("%s", clientName);
            WebElement select_client_option = driver.findElement(By.xpath(client_dynamic_locator));
                Constants.doClick(select_client_option);
//            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
//            WebElement select_client_option = wait.until(
//                    ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='select2-results__options']//li[text()='Dr. Elida Hegmann (2025022710456)']"))
//            );


             ///Select Meeting Mode
            WebElement meeting_mode_dd = LocatorUtils.getElement(locator_file, "meeting_mode_select");
            Constants.doClick(meeting_mode_dd);
            String  meeting_locator_template=LocatorUtils.getLocator(locator_file,"meeting_mode_option").toString();
            meeting_locator_template = meeting_locator_template.replace("By.xpath: ", "");
            // Replace %s with actual client name in the locator
            String meeting_dynamic_locator = meeting_locator_template.replace("%s", meetingMode);
            WebElement meeting_mode_option = driver.findElement(By.xpath(meeting_dynamic_locator));
            Constants.doClick(meeting_mode_option);
           if(meetingMode.equalsIgnoreCase("Whatsapp")){
               WebElement whatsapp_no=LocatorUtils.getElement(locator_file,"whatsapp_no_locator");
               Thread.sleep(500);
               Constants.enterValues(whatsapp_no,Constants.enterDynamicData("phone_no"));
           }
           if(meetingMode.equalsIgnoreCase("Video Call")){
               WebElement url=LocatorUtils.getElement(locator_file,"video_call_link");
               Constants.enterValues(url,"https://meet.google.com/kae-jvxz-zfr");
           }

            // Select Date & Time

            WebElement date_time_dd = LocatorUtils.getElement(locator_file, "date_time_dropdown");
            Constants.doClick(date_time_dd);
            WebElement month_dd = LocatorUtils.getElement(locator_file, "select_month_dd");
            Select sel = new Select(month_dd);
            sel.selectByVisibleText(month);
            String date_locator_template=LocatorUtils.getLocator(locator_file,"select_date").toString();
            date_locator_template=date_locator_template.replace("By.xpath: ","");
            String date_dynamic_locator=date_locator_template.replace("%s",meeting_date);
            WebElement date_appointment=driver.findElement(By.xpath(date_dynamic_locator));
            Constants.doClick(date_appointment);
           // WebElement date_appointment=LocatorUtils.getElement(locator_file,"select_date");

            WebElement time_hrs = LocatorUtils.getElement(locator_file, "time_hrs");
            time_hrs.sendKeys(time);
//            WebElement time_am_pm = LocatorUtils.getElement(locator_file, "time_am_pm");
//            Constants.doClick(time_am_pm);

            // Finalize Appointment
            WebElement done_btn = LocatorUtils.getElement(locator_file, "done_btn");
            Constants.doClick(done_btn);
            WebElement description = LocatorUtils.getElement(locator_file, "description");
            description.sendKeys(descriptionText);
            WebElement book_btn=LocatorUtils.getElement(locator_file,"book_btn");
           Constants.doClick(book_btn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointment_booked(){
        try{
            System.out.println("Appointment Booked");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    public void check_appointments(){
        try{
            List<WebElement> check_buttons=LocatorUtils.getElements(locator_file,"view_appointments");
            for(int i=0;i<check_buttons.size();i++){
                Constants.doClick(check_buttons.get(i));
                JavascriptExecutor js=(JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,800)","");
                Thread.sleep(500);
                js.executeScript("window.scrollBy(0,-800)","");
                Thread.sleep(1000);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void appointments_displayed(){
        System.out.println("Message : Appointments displayed");
    }


    public void select_appointment(){
        try{

        WebElement month_view=LocatorUtils.getElement(locator_file,"view_month");
        Constants.doClick(month_view);
        WebElement next_month=LocatorUtils.getElement(locator_file,"month_next_btn");
        Constants.doClick(next_month);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void calendar_actions(){
        try{

            reschedule_meeting();
            Thread.sleep(500);
            approve_meeting();
            Thread.sleep(500);
            view_meeting();
            Thread.sleep(500);
            //view_profile();
            delete_appointment();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void view_meeting(){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;

// Re-fetch just before scrolling
            WebElement select_meeting = LocatorUtils.getElement(locator_file, "select_meeting");
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", select_meeting);

// Re-fetch again before click to avoid stale
            select_meeting = LocatorUtils.getElement(locator_file, "select_meeting");
            Constants.doClick(select_meeting);

            WaitUtils.implicitWait();
            WebElement view_meeting=LocatorUtils.getElement(locator_file,"view_appointment_action");
            Constants.doClick(view_meeting);
            Thread.sleep(500);
            WebElement close_view_appointment=LocatorUtils.getElement(locator_file,"close_view_appointment");
            Constants.doClick(close_view_appointment);
            System.out.println("Meeting viewed");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void reschedule_meeting(){
        try{
            WebElement select_meeting=LocatorUtils.getElement(locator_file,"select_meeting");
            Constants.doClick(select_meeting);
            WebElement reschedule_meeting=LocatorUtils.getElement(locator_file,"reschedule_meeting_action");
            Constants.doClick(reschedule_meeting);
            WaitUtils.implicitWait();
            WebElement date_time_dd = LocatorUtils.getElement(locator_file, "date_time_dropdown");
            Constants.doClick(date_time_dd);
            WebElement time_hrs = LocatorUtils.getElement(locator_file, "time_hrs");
            WebElement arrow_up=LocatorUtils.getElement(locator_file,"reschedule_time_arrow_up");
            Constants.doClick(arrow_up);
            WebElement done_btn = LocatorUtils.getElement(locator_file, "done_btn");
            Constants.doClick(done_btn);
            Thread.sleep(1000);
            WebElement save_btn=LocatorUtils.getElement(locator_file,"save_btn");
            Constants.doClick(save_btn);
            System.out.println("Meeting rescheduled");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void approve_meeting(){
        try{
            WebElement month_view=LocatorUtils.getElement(locator_file,"view_month");
            Constants.doClick(month_view);
            WebElement next_month=LocatorUtils.getElement(locator_file,"month_next_btn");
            Constants.doClick(next_month);
            WebElement select_meeting=LocatorUtils.getElement(locator_file,"select_meeting");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", select_meeting);
            Constants.doClick(select_meeting);
            WebElement approve_meeting=LocatorUtils.getElement(locator_file,"approve_appointment_action");
            Constants.doClick(approve_meeting);
            System.out.println("Meeting approved");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void view_profile(){
        try{
            WebElement select_meeting=LocatorUtils.getElement(locator_file,"select_meeting");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", select_meeting);
            Constants.doClick(select_meeting);
            WebElement view_profile=LocatorUtils.getElement(locator_file,"view_profile_action");
            WaitUtils.implicitWait();
            js.executeScript("arguments[0].scrollIntoView(true);", view_profile);
            Constants.doClick(view_profile);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void delete_appointment(){
        try{
            WebElement select_meeting=LocatorUtils.getElement(locator_file,"select_meeting");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", select_meeting);
            Constants.doClick(select_meeting);
            WebElement delete_meeting_action=LocatorUtils.getElement(locator_file,"delete_meeting_action");
            js.executeScript("arguments[0].scrollIntoView(true);", delete_meeting_action);
            Thread.sleep(1000);
            Constants.doClick(delete_meeting_action);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement modal = LocatorUtils.getElement(locator_file,"delete_appoinment_modal");
            WaitUtils.implicitWait();
            System.out.println("modal text= "+modal.getText());
            driver.switchTo().activeElement();
            WebElement yes_btn=LocatorUtils.getElement(locator_file,"yes_btn");
            Constants.doClick(yes_btn);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void verify_action(){
        try{
            System.out.println("All calendar actions performed well.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
