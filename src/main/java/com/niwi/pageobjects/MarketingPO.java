package com.niwi.pageobjects;

import com.niwi.utils.Constants;
import com.niwi.utils.LocatorUtils;
import com.niwi.utils.WaitUtils;
import com.niwi.utils.WindowUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.scanner.Constant;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class MarketingPO {
    WebDriver driver;
    String locator_file="marketing_locators.properties";
    public MarketingPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void user_clicks_on_marketing_tab(){
        try{
            WebElement marketing_tab= LocatorUtils.getElement(locator_file,"marketing_tab");
            Constants.doClick(marketing_tab);
            WebElement leads=WaitUtils.getWait(driver,10).until(ExpectedConditions.elementToBeClickable(LocatorUtils.getElement(locator_file,"leads_tab")));
            Constants.doClick(leads);
//            WaitUtils.getWait(driver, 10)
//                    .until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator(locator_file, "leads_page_header")));

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void user_creates_lead(){
        try{
            String[] values1={"insta","camp1","tv ad","form1"};
            String[] values2={"Facebook","camp2","insta ad","form2"};
            String[] values3={"party","camp1","tv ad","form1"};
            String[] values4={"party","camp2","tv ad","form 2"};
            create_lead(values1);
            Thread.sleep(500);
            create_lead(values2);
            Thread.sleep(500);
            //create_lead(values3);
            Thread.sleep(500);
            create_lead(values4);


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void create_lead(String[] values){
        try{
            String joined_values=String.join(" | ",values);
            WebElement add_lead_btn=WaitUtils.getWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator(locator_file,"add_lead_btn")));
            Constants.doClick(add_lead_btn);
            WaitUtils.getWait(driver,5);
            //lead form
            WebElement name=LocatorUtils.getElement(locator_file,"lead_name");
            Constants.enterValues(name,Constants.enterDynamicData("name"));

            WebElement email=LocatorUtils.getElement(locator_file,"lead_email");
            Constants.fillAndReturn(email,"email_id");
            System.out.println(email.getText());

            WebElement phone=LocatorUtils.getElement(locator_file,"lead_phone");
            Constants.enterValues(phone,Constants.enterDynamicData("phone_no"));

            WebElement location=LocatorUtils.getElement(locator_file,"location");
            Constants.enterValues(location,"ludhiana");

            WebElement source=LocatorUtils.getElement(locator_file,"lead_source");
            Constants.enterValues(source,joined_values);

            WebElement add_btn=LocatorUtils.getElement(locator_file,"add_lead");
            Constants.doClick(add_btn);



        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void lead_gets_created(){
        try{
            driver.navigate().refresh();
            List<WebElement> lead_list=LocatorUtils.getElements(locator_file,"lead_created");
            for(int i=0;i<lead_list.size();i++) {
                System.out.println(lead_list.get(i).getText());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void user_opens_webpage(){
        try {
            WebDriverWait wait = WaitUtils.getWait(driver, 20);
            WebElement setting_tab = LocatorUtils.getElement(locator_file, "settings_tab");

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", setting_tab
            );

            WaitUtils.getWait(driver, 10).until(ExpectedConditions.elementToBeClickable(setting_tab));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", setting_tab);

//            WaitUtils.getWait(driver,20);
//            WebElement setting_tab=LocatorUtils.getElement(locator_file,"settings_tab");
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", setting_tab);
//            WaitUtils.getWait(driver,10).until(ExpectedConditions.elementToBeClickable(setting_tab));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", setting_tab);


            WebElement my_webpage=LocatorUtils.getElement(locator_file,"my_webpage_tab");
            Constants.doClick(my_webpage);
            WaitUtils.getWait(driver,20);
            WebElement copy_link=LocatorUtils.getElement(locator_file,"copy_webpage_link");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",copy_link);
            Constants.doClick(copy_link);
            String copied_value= (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .getData(DataFlavor.stringFlavor);
            System.out.println(copied_value);
            ((JavascriptExecutor) driver).executeScript("window.open()");
//            Set<String> all_ids=driver.getWindowHandles();
//            ArrayList<String> tabs=new ArrayList<>(all_ids);
//            driver.switchTo().window(tabs.get(1));
            WindowUtils.switchToWindow(driver,1);
            driver.get(copied_value);
            WaitUtils.getWait(driver,20);

        }
        catch (TimeoutException e) {
            System.out.println("TimeoutException: Element not clickable in time: " + e.getMessage());

        } catch (ElementNotInteractableException e) {
            System.out.println("ElementNotInteractableException: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Unexpected Exception: " + e.getMessage());
        }

    }

    public void create_lead_From_webpage(){
        try{
            WebElement name=LocatorUtils.getElement(locator_file,"name");
            Constants.enterValues(name,Constants.enterDynamicData("name"));
            WebElement contact=LocatorUtils.getElement(locator_file,"contact");
            Constants.enterValues(contact,Constants.enterDynamicData("phone_no"));
            WebElement email=LocatorUtils.getElement(locator_file,"email");
            Constants.enterValues(email,Constants.enterDynamicData("email_id"));
            WebElement gender=LocatorUtils.getElement(locator_file,"gender");
            Select gender_option=new Select(gender);
            gender_option.selectByValue("male");
           // WebElement cap=LocatorUtils.getElement(locator_file,"captcha");
//            if(!cap.isSelected())
//            {
//                cap.click();
//            }
            WebElement submit_button=LocatorUtils.getElement(locator_file,"submit_button");
            Constants.doClick(submit_button);
            WindowUtils.switchToWindow(driver,0);
            user_clicks_on_marketing_tab();


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void search_leads(){
        try {
            WebElement search_input = WaitUtils.getWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator(locator_file, "advance_search_textbox")));
            Constants.enterValues(search_input, "test");
            WaitUtils.getWait(driver, 30)
                    .until(ExpectedConditions.invisibilityOfElementLocated(LocatorUtils.getLocator(locator_file,"loader")));
//            By leadsFilteredLocator = LocatorUtils.getLocator(locator_file, "leads_filtered");
            // Optionally, you could wait for the leads to become visible or for a specific element indicating that filtering is complete
            //WaitUtils.getWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator(locator_file, "leads_filtered")));
//            List<WebElement> filter_leads = LocatorUtils.getElements(locator_file,"leads_filtered");
//            List<String> leadNames = new ArrayList<>();
//            for (WebElement lead : filter_leads) {
//                leadNames.add(lead.getText().trim());
//            }
//            for (String name : leadNames) {
//                System.out.println(name);
//            }

            search_input.clear();
            WaitUtils.getWait(driver,50);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void click_advance_search_btn(){
        try{
            WebElement advance_search_btn=WaitUtils.getWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator(locator_file,"advance_search_btn")));
          ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-500)","");
            Thread.sleep(500);
            Constants.doClick(advance_search_btn);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void submit_filters(){
        try{
            WebElement select_date=LocatorUtils.getElement(locator_file,"select_from_to_date");
            Constants.doClick(select_date);
            WebElement from_date=LocatorUtils.getElement(locator_file,"from_date");
            Constants.doClick(from_date);
            WebElement to_date=LocatorUtils.getElement(locator_file,"to_date");
            Constants.doClick(to_date);
            WaitUtils.getWait(driver,5);
            //lead type
            WebElement lead_type=LocatorUtils.getElement(locator_file,"select_lead_type");
            Select select_lead_type=new Select(lead_type);
            select_lead_type.selectByVisibleText("Warm");
            //location
//            WebElement lead_location=LocatorUtils.getElement(locator_file,"select_location");
//            Select select_lead_location=new Select(lead_location);
//            select_lead_location.selectByVisibleText("ludhiana");

            //lead source
            WebElement lead_source=LocatorUtils.getElement(locator_file,"select_source");
            Select select_lead_source=new Select(lead_source);
           // select_lead_source.selectByVisibleText("insta");
            select_lead_source.selectByVisibleText("Facebook");
           // select_lead_source.selectByVisibleText("party ");

            //campaign
            WebElement lead_campaign=LocatorUtils.getElement(locator_file,"select_campaign");
            Select select_lead_campaign=new Select(lead_campaign);
            select_lead_campaign.selectByVisibleText("camp1");
            select_lead_campaign.selectByVisibleText("camp2");

            //ad
            WebElement lead_ad=LocatorUtils.getElement(locator_file,"select_ad_name");
            Select select_lead_ad=new Select(lead_ad);
            select_lead_ad.selectByVisibleText("tv ad");
            select_lead_ad.selectByVisibleText("insta ad");

            //form
            WebElement lead_form=LocatorUtils.getElement(locator_file,"select_form");
            Select select_lead_form=new Select(lead_form);
            select_lead_form.selectByVisibleText("form1");
            select_lead_form.selectByVisibleText("form2");

            //submit
            WebElement submit_btn=LocatorUtils.getElement(locator_file,"submit_search_btn");
            Constants.doClick(submit_btn);
            WaitUtils.getWait(driver,60);
            //click_advance_search_btn();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void filtered_results(){
        try{
            WaitUtils.getWait(driver, 30)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingSpinner")));

            // Wait for any result card to appear AFTER filtering
            By filteredLeadsLocator = LocatorUtils.getLocator(locator_file, "leads_filtered");
            WaitUtils.getWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(filteredLeadsLocator));

            // Re-fetch the list AFTER DOM updates
            List<WebElement> leads_filtered = driver.findElements(filteredLeadsLocator);
            WaitUtils.getWait(driver,40);
            System.out.println("Results are as following:");
            for (WebElement lead : leads_filtered) {
                System.out.println(lead.getText());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void edit_lead(){
        try{
            List<WebElement> leads=LocatorUtils.getElements(locator_file,"leads_searched");

            for(int i=0;i<leads.size();i++){
                Constants.doClick(leads.get(1));
            }
            Thread.sleep(500);
            WaitUtils.getWait(driver,20);
            WebElement edit_btn=LocatorUtils.getElement(locator_file,"edit_btn");
           // ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",edit_btn);
            Thread.sleep(500);
            Constants.doClick(edit_btn);

            WebElement lead_status=LocatorUtils.getElement(locator_file,"edit_status");
            Select status=new Select(lead_status);
            status.selectByVisibleText("Follow-up");


            WebElement lead_counsellor=LocatorUtils.getElement(locator_file,"edit_counsellor");
            Select counsellor=new Select(lead_counsellor);
            counsellor.selectByVisibleText("Chaitanya Puri");

            WebElement lead_email=LocatorUtils.getElement(locator_file,"edit_email");
            lead_email.clear();
            Constants.enterValues(lead_email,Constants.enterDynamicData("email_id"));

            WebElement lead_action=LocatorUtils.getElement(locator_file,"edit_action");
            Select action=new Select(lead_action);
            action.selectByVisibleText("Wants a free counselling session");
            WebElement edit_submit=LocatorUtils.getElement(locator_file,"edit_submit_btn");
            Constants.doClick(edit_submit);
            WaitUtils.getWait(driver,20).until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-message")));
//            WebElement modal=LocatorUtils.getElement(locator_file,"edit_modal");
//            driver.switchTo().activeElement();
//            WebElement close_btn=LocatorUtils.getElement(locator_file,"close_edit");
//            Constants.doClick(close_btn);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void lead_gets_edited(){
        try{
            WebElement show_more=LocatorUtils.getElement(locator_file,"show_more_btn");
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",show_more);
            Constants.doClick(show_more);
            WaitUtils.getWait(driver,30);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void add_comments(){
        try{
            WebElement text_input=LocatorUtils.getElement(locator_file,"comments_input");
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",text_input);

            Constants.enterValues(text_input,"This comment is for testing leads");
            Thread.sleep(500);
            WebElement add_comment_btn=LocatorUtils.getElement(locator_file,"add_comment_btn");
            Constants.doClick(add_comment_btn);
            WaitUtils.getWait(driver,20);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void display_comments(){
        try{
            WebElement display_comment=LocatorUtils.getElement(locator_file,"display_comment");
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",display_comment);
            System.out.println("Latest comment= "+display_comment.getText());
            WaitUtils.getWait(driver,40);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
