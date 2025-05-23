package com.niwi.pageobjects;

import com.niwi.utils.Constants;
import com.niwi.utils.LocatorUtils;
import com.niwi.utils.ReadFiles;
import com.niwi.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.invoke.ConstantCallSite;
import java.security.CodeSigner;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;

public class SalesPO {
    String locator_file="salesmodule_locators.properties";
    String data_file="salesmodule_data.properties";
    WebDriver driver;
    WebDriverWait wait ;
    private static String first_name_value;
    public SalesPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void sales_tab(){
        try{
            WebElement sales_tab= LocatorUtils.getElement(locator_file,"sales_tab");
            Constants.doClick(sales_tab);
            Thread.sleep(2000);
            WebElement create_invoice= LocatorUtils.getElement(locator_file,"create_invoice_button");
            Constants.doClick(create_invoice);
            WaitUtils.implicitWait();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void tax_exclusive(){
        try{
            WebElement ex_tax_dropdown=LocatorUtils.getElement(locator_file,"exclusive_tax_dropdown");
            Constants.doClick(ex_tax_dropdown);
            WebElement ex_tax_choose=LocatorUtils.getElement(locator_file,"exclusive_tax_choose");
            Constants.doClick(ex_tax_choose);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void create_new_client(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
             WebElement client_dropdown=LocatorUtils.getElement(locator_file,"select_client_dropdown");
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", client_dropdown);
            Thread.sleep(1000); // Small delay to let the page adjust
            Constants.doClick(client_dropdown);
            WebElement create_new_client =LocatorUtils.getElement(locator_file, "add_new_client_invoice");
            Constants.doClick(create_new_client);
            //name
            WebElement first_name=LocatorUtils.getElement(locator_file,"first_name");
            first_name_value=Constants.enterDynamicData("name");
            Constants.enterValues(first_name,first_name_value);
            System.out.println("Name -" +first_name_value);
            //email
            WebElement email_id=LocatorUtils.getElement(locator_file,"email");
           // String email_value= ReadFiles.getDataFromPropertyFile(data_file,"email");
            Constants.enterValues(email_id,Constants.enterDynamicData("email_id"));
            //phone no
            WebElement phone_no=LocatorUtils.getElement(locator_file,"phone_no");
            Constants.enterValues(phone_no,Constants.enterDynamicData("phone_no"));


            //state
            WebElement state=LocatorUtils.getElement(locator_file,"state");
            Constants.doClick(state);
            Thread.sleep(1000);
            WebElement state_select=LocatorUtils.getElement(locator_file,"state_select_option");
            Constants.doClick(state_select);

            //city
            WebElement city=LocatorUtils.getElement(locator_file,"city");
            String city_value= ReadFiles.getDataFromPropertyFile(data_file,"city");
            Constants.enterValues(city,city_value);

            //dietician
            WebElement counsellor_dropdown=LocatorUtils.getElement(locator_file,"counsellor_dropdown");
            Constants.doClick(counsellor_dropdown);
            WebElement dietician_select=LocatorUtils.getElement(locator_file,"dietician");
            Constants.doClick(dietician_select);
            //add client button
            WebElement add_btn=LocatorUtils.getElement(locator_file,"add_client_button");
            Constants.doClick(add_btn);

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Element not clickable, trying JavaScript click...");
        }
    }

    public void create_plan(){
        try{
                selectPlan("weight_loss_plan","2");
//                WaitUtils.implicitWait();
//                selectPlan("detox_plan","1");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void selectPlan(String planLocator,String planQty){
        try {

            WebElement add_plan_btn = LocatorUtils.getElement(locator_file, "add_plan_button");
            Constants.doClick(add_plan_btn);
            Thread.sleep(2000);
            WebElement select_plan = LocatorUtils.getElement(locator_file, "select_plan");
            Constants.doClick(select_plan);
            WebElement plan1 = LocatorUtils.getElement(locator_file,"plan_name");
            Constants.doClick(plan1);
            WebElement hsn_code = LocatorUtils.getElement(locator_file,"hsn_code");
           hsn_code.sendKeys(Keys.CONTROL + "a"); // Select all text
            hsn_code.sendKeys(Keys.DELETE); // Delete text
           Constants.enterValues(hsn_code,Constants.enterDynamicData("number"));
            WebElement qty = LocatorUtils.getElement(locator_file, "plan_qty");
            qty.sendKeys(Keys.CONTROL + "a"); // Select all text
            qty.sendKeys(Keys.DELETE); // Delete text
            Constants.enterValues(qty, "2");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void add_discount(){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement discount=LocatorUtils.getElement(locator_file,"discount_coupon");
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", discount);
            Thread.sleep(1000); // Small delay to let the page adjust
            Constants.doClick(discount);
            WebElement create_discount=LocatorUtils.getElement(locator_file,"create_discount_coupon");
            Constants.doClick(create_discount);
            WebElement name=LocatorUtils.getElement(locator_file,"display_name");
            Constants.enterValues(name,Constants.enterDynamicData("name"));
            WebElement coupon_code=LocatorUtils.getElement(locator_file,"coupon_code");
            Constants.enterValues(coupon_code,Constants.enterDynamicData("code"));
            WebElement amount=LocatorUtils.getElement(locator_file,"discount_amount");
            Constants.enterValues(amount,"500");
            WebElement add_btn=LocatorUtils.getElement(locator_file,"add_discount_button");
            Constants.doClick(add_btn);


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void invoice_created(){
        try{
            WaitUtils.implicitWait();
//            WebElement notes=LocatorUtils.getElement(locator_file,"notes");
//            Constants.enterValues(notes,"Invoice calculations done");
           JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement save_invoice=LocatorUtils.getElement(locator_file,"save_invoice_btn");
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", save_invoice);
            Thread.sleep(1000); // Small delay to let the page adjust
            Constants.doClick(save_invoice);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update_cps(){
        try{
            WebElement second_client_dropdown=LocatorUtils.getElement(locator_file,"second_client_dropdown");
            Constants.doClick(second_client_dropdown);
            WebElement second_client_input=LocatorUtils.getElement(locator_file,"second_client_search_input");
            Constants.doClick(second_client_input);
            Constants.enterValues(second_client_input,"Adsas909");
            WebElement second_client_option=LocatorUtils.getElement(locator_file,"second_client_option_choose");
            Constants.doClick(second_client_option);
//            WebElement start_date=LocatorUtils.getElement(locator_file,"select_start_date");
//            Constants.doClick(start_date);
//            WebElement todayDate = driver.findElement(By.xpath("(//div[@class='flatpickr-calendar animate arrowTop arrowLeft']//div[@class='flatpickr-innerContainer']//span[@class='flatpickr-day today selected'])[last()]"));
//           Constants.doClick(todayDate);
            WebElement sales_remarks=LocatorUtils.getElement(locator_file,"sales_remarks");
            Constants.enterValues(sales_remarks,"Sales invoice created");
            WebElement save_button=LocatorUtils.getElement(locator_file,"save_btn");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save_button);
            WebElement modal=LocatorUtils.getElement(locator_file,"cps_modal");
            System.out.println("Inside modal");
            driver.switchTo().activeElement();
            WebElement yes_btn=LocatorUtils.getElement(locator_file,"cps_yes");
            Constants.doClick(yes_btn);
            Thread.sleep(2000);
            String curl=driver.getCurrentUrl();
            Assert.assertEquals(curl.contains("sales_view"),true);
            System.out.println("Invoice created successfully");
            List<WebElement> payment_balance=LocatorUtils.getElements(locator_file,"payment_balance_before");
            System.out.println("Total Invoice Amount =" +payment_balance.get(0).getText());
            System.out.println("****");
            List<WebElement> new_client_invoice=LocatorUtils.getElements(locator_file,"new_user_invoice");
            String invoice_no=new_client_invoice.get(0).getText();
            System.out.println(invoice_no);
            Constants.doClick(new_client_invoice.get(0));
            Thread.sleep(1000);
            driver.navigate().back();
            //export options
//            WebElement export_btn=LocatorUtils.getElement(locator_file,"export_btn");
//            Constants.doClick(export_btn);
//            System.out.println("Downloading CSV file");
//            WebElement csv_btn=LocatorUtils.getElement(locator_file,"csv_btn");
//            Constants.doClick(csv_btn);
//            Thread.sleep(2000);
//            System.out.println("Downloading Excel file");
//            WebElement excel_btn=LocatorUtils.getElement(locator_file,"excel_btn");
//            Constants.doClick(excel_btn);
//            Thread.sleep(2000);
//            System.out.println("Downloading Pdf file");
//            WebElement pdf_btn=LocatorUtils.getElement(locator_file,"pdf_btn");
//            Constants.doClick(pdf_btn);
//            System.out.println("Copy  file");
//            WebElement copy_btn=LocatorUtils.getElement(locator_file,"copy_btn");
//            Constants.doClick(copy_btn);
//
//            System.out.println("Export options completed");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement client_programme=LocatorUtils.getElement(locator_file,"client_programme_tab");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", client_programme);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", client_programme);
            //Constants.doClick(client_programme);
            WebElement status_dropdown=LocatorUtils.getElement(locator_file,"status_dropdown_select");
            WaitUtils.implicitWait();
            Constants.doClick(status_dropdown);
            WebElement status=LocatorUtils.getElement(locator_file,"all_status");
            Constants.doClick(status);
            WebElement submit_btn=LocatorUtils.getElement(locator_file,"submit_status_btn");
            Constants.doClick(submit_btn);
            WebElement search_box=LocatorUtils.getElement(locator_file,"client_programme_search_box");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", search_box);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", search_box);
            //Constants.doClick(search_box);
            Constants.enterValues(search_box,invoice_no);
            ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,400)","");
            Thread.sleep(2000);
            List<WebElement> client_status_list=LocatorUtils.getElements(locator_file,"client_programme_status_list");
            for(int client=0;client<client_status_list.size();client++)
            {
                System.out.println(client_status_list.get(client).getText());
            }
            Thread.sleep(1000);
            //((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-900)"," ");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void running_options(){
        try{
            program_hold();
            WaitUtils.implicitWait();
            program_resume();
            WaitUtils.implicitWait();
            program_break();
           // dormant_to_running();



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void program_hold(){
        WebElement dots_btn=LocatorUtils.getElement(locator_file,"running_actions_button");
        Constants.doClick(dots_btn);
        WebElement on_hold=LocatorUtils.getElement(locator_file,"program_hold_option");
        Constants.doClick(on_hold);
        System.out.println("Programmed hold succesfully");
    }
    public void program_resume(){
        WebElement dots_btn=LocatorUtils.getElement(locator_file,"onhold_actions_button");
        Constants.doClick(dots_btn);
        WebElement resume=LocatorUtils.getElement(locator_file,"resume_option");
        Constants.doClick(resume);
        System.out.println("Programmed hold succesfully");
    }
    public void program_break() {
        try {
            WebElement dots_btn = LocatorUtils.getElement(locator_file, "running_actions_button");
            Constants.doClick(dots_btn);
            WebElement onbreak = LocatorUtils.getElement(locator_file, "onbreak_option");
            Constants.doClick(onbreak);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("on-break-modal-dialog")));
            driver.switchTo().activeElement();
            //from date
            WebElement from_date = driver.findElement(By.xpath("//input[@id='cpi_break_date']"));
            from_date.click();
            Thread.sleep(1000);
            WebElement month_dd = driver.findElement(By.xpath("//div[@class='flatpickr-month']//select[@class='flatpickr-monthDropdown-months']"));
            Select sel = new Select(month_dd);
            sel.selectByValue("3");
            Thread.sleep(1000);
            WebElement date_val=driver.findElement(By.xpath("//div[@class='flatpickr-days']//div[@class='dayContainer']//span[@aria-label='May 1, 2025']"));
            date_val.click();
            Thread.sleep(2000);
        WebElement break_submit=LocatorUtils.getElement(locator_file,"onbreak_submit_btn");
        Constants.doClick(break_submit);
        System.out.println("Programmed onbreak succesfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click_receipt_master(){
        try{
            WebElement receipt_master=LocatorUtils.getElement(locator_file,"receipt_master_tab");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", receipt_master);
            //Constants.doClick(receipt_master);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void add_payment(){
        try{
            if (first_name_value == null) {
                throw new NullPointerException("first_name_value is null! Make sure create_new_client() is called first.");
            }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            System.out.println("First name value= "+first_name_value);
            WebElement add_payment=LocatorUtils.getElement(locator_file,"add_payment_btn");
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-400)"," ");
            wait.until(ExpectedConditions.elementToBeClickable(add_payment)).click();

            WebElement select_client=LocatorUtils.getElement(locator_file,"select_payment_client_dropdown");
            Constants.doClick(select_client);
            WebElement client_name=LocatorUtils.getElement(locator_file,"payment_client_name");
            Constants.doClick(client_name);
            Constants.enterValues(client_name,first_name_value);
            Thread.sleep(2000);
            WebElement client_name_click=LocatorUtils.getElement(locator_file,"payment_client_name_click");
            Constants.doClick(client_name_click);
            WebElement currency_select=LocatorUtils.getElement(locator_file,"currency_select");
            Constants.doClick(currency_select);
            WebElement currency_option=LocatorUtils.getElement(locator_file,"currency_select_option");
            Constants.doClick(currency_option);
            WebElement payment_amount=LocatorUtils.getElement(locator_file,"payment_amount");
            Constants.enterValues(payment_amount,"2000");
            WebElement payment_mode_select=LocatorUtils.getElement(locator_file,"payment_mode_select");
            Constants.doClick(payment_mode_select);
            WebElement payment_mode_option=LocatorUtils.getElement(locator_file,"payment_mode_option");
            Constants.doClick(payment_mode_option);
            WebElement save_payment_btn=LocatorUtils.getElement(locator_file,"save_payment_btn");
            Constants.doClick(save_payment_btn);
            List<WebElement> receipt_table_row=LocatorUtils.getElements(locator_file,"badge_text");
            System.out.println(receipt_table_row.get(0).getText());
            if (receipt_table_row.get(0).getText().equalsIgnoreCase("Yes")) {
                System.out.println("Payment done in advance");
            }
            else {
                System.out.println("Payment not done in advance");
            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void link_payment(){
        try{
            WebElement sales_master=LocatorUtils.getElement(locator_file,"sales_master_tab");
            Constants.doClick(sales_master);
            WebElement actions=LocatorUtils.getElement(locator_file,"actions");
            Constants.doClick(actions);
            WebElement add_payment_option=LocatorUtils.getElement(locator_file,"actions_add_payment");
            Constants.doClick(add_payment_option);
            WebElement select_client_dropdown=LocatorUtils.getElement(locator_file,"select_advance_payment_client_dropdown");
           Constants.doClick(select_client_dropdown);
           WebElement client_name_select=LocatorUtils.getElement(locator_file,"advance_payment_client_select");
           Constants.doClick(client_name_select);
           Thread.sleep(1000);
            WebElement save_payment_btn=LocatorUtils.getElement(locator_file,"save_payment_btn");
            Constants.doClick(save_payment_btn);
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void check_balance(){
        try{
            List<WebElement> payment_balance_after=LocatorUtils.getElements(locator_file,"payment_balance_after");
            System.out.println("Payment balance after linking with reciept =" +payment_balance_after.get(0).getText());
            //Actions
            programme_details_action();
            Thread.sleep(2000);
            edit_invoice_action();
            add_payment_action();
            edit_invoice_action();
            download_invoice_action();
            delete_invoice_action();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void programme_details_action(){
        try{
            WaitUtils.implicitWait();
            WebElement three_dots=LocatorUtils.getElement(locator_file,"three_dots");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", three_dots);
            //Constants.doClick(three_dots);
            WebElement programme_details=LocatorUtils.getElement(locator_file,"programme_details_action");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", programme_details);
            Thread.sleep(2000);
            driver.navigate().back();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void add_payment_action(){
        try{
            WebElement three_dots=LocatorUtils.getElement(locator_file,"three_dots");
            Constants.doClick(three_dots);
            WebElement add_payment=LocatorUtils.getElement(locator_file,"add_payment_action");
            Constants.doClick(add_payment);
            WebElement payment_mode_select=LocatorUtils.getElement(locator_file,"payment_mode_select");
            Constants.doClick(payment_mode_select);
            WebElement payment_mode_option=LocatorUtils.getElement(locator_file,"payment_mode_option");
            Constants.doClick(payment_mode_option);
            WebElement save_btn=LocatorUtils.getElement(locator_file,"save_payment_btn");
            Constants.doClick(save_btn);
            Thread.sleep(2000);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void edit_invoice_action(){
        try{
            WebElement three_dots=LocatorUtils.getElement(locator_file,"three_dots");
            Constants.doClick(three_dots);
            //WebElement payment_balance=LocatorUtils.getElement(locator_file,"payment_balance_paid");
            WebElement edit_invoice=LocatorUtils.getElement(locator_file,"edit_invoice_action");
            Constants.doClick(edit_invoice);
//            WebElement modal_text=LocatorUtils.getElement(locator_file,"edit_invoice_modal_text");
//            System.out.println("Edit Invoice Message="+modal_text.getText());
//            Thread.sleep(1000);
//            driver.switchTo().activeElement();
//            WebElement ok_btn=LocatorUtils.getElement(locator_file,"modal_ok_btn");
//            Constants.doClick(ok_btn);
            Thread.sleep(2000);
            driver.navigate().back();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void download_invoice_action(){
        try{
            WebElement three_dots=LocatorUtils.getElement(locator_file,"three_dots");
            Constants.doClick(three_dots);
            WebElement download_invoice=LocatorUtils.getElement(locator_file,"download_invoice_action");
            Constants.doClick(download_invoice);
            System.out.println("Invoice downloaded successfully");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delete_invoice_action(){
        try{
            WebElement three_dots=LocatorUtils.getElement(locator_file,"three_dots");
            Constants.doClick(three_dots);
            WebElement delete_invoice=LocatorUtils.getElement(locator_file,"delete_invoice_action");
            Constants.doClick(delete_invoice);
            WebElement delete_modal=LocatorUtils.getElement(locator_file,"delete_modal");
            driver.switchTo().activeElement();
            //WebElement modal_yes=LocatorUtils.getElement(locator_file,"delete_modal_ok");
            //Constants.doClick(modal_yes);
            // System.out.println("Invoice deleted successfully");
            WebElement modal_no=LocatorUtils.getElement(locator_file,"delete_modal_no");
            Constants.doClick(modal_no);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void click_client_programme(){
        try{
            WebElement client_programme_tab=LocatorUtils.getElement(locator_file,"client_programme_tab");
            Constants.doClick(client_programme_tab);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handle_status() {
        try {
            check_status_list("all_status");
            Thread.sleep(2000);
            check_status_list("running_status");
            Thread.sleep(2000);
            check_status_list("dormant_status");
            Thread.sleep(2000);
            check_status_list("onbreak_status");
            Thread.sleep(2000);
            check_status_list("completed_status");
            Thread.sleep(2000);
            check_status_list("upgraded_status");
            Thread.sleep(2000);
            check_status_list("programme_paused_status");
            Thread.sleep(2000);
            check_status_list("programme_refunded_status");
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void check_status_list(String status_type){
        try{
            WebElement status_dropdown=LocatorUtils.getElement(locator_file,"status_dropdown_select");
            Constants.doClick(status_dropdown);
           WebElement status=LocatorUtils.getElement(locator_file,status_type);
           Constants.doClick(status);

//            // 1. Open the date picker
//            WebElement datePicker = driver.findElement(By.id("from_date"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", datePicker);
//
//            Thread.sleep(1000); // Small delay to let the calendar load
//            WebElement month_dd=driver.findElement(By.xpath("//div[@class='flatpickr-months']//select[@class='flatpickr-monthDropdown-months']"));
//            Select sel=new Select(month_dd);
//            sel.selectByValue("1");
//            String target_date = "February 6, 2025";
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement date_val = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//span[@aria-label='" + target_date + "' and contains(@class, 'flatpickr-day')]")
//            ));
//
//            // 4. Use Actions to avoid ElementNotInteractableException
//            Actions actions = new Actions(driver);
//            actions.moveToElement(date_val).click().perform();
//
//
//
//
//
//            Thread.sleep(2000);
           WebElement submit_btn=LocatorUtils.getElement(locator_file,"submit_status_btn");
           Constants.doClick(submit_btn);
            WaitUtils.implicitWait();
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600)"," ");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-600)"," ");

            System.out.println("Data  Available on this page: /n");
            List<WebElement> data=LocatorUtils.getElements(locator_file,"package_data");
            for(WebElement list:data){
                System.out.println(list.getText());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


//Sales Report
    public void select_financial_year(){
        try{
//            WebElement sales_tab= LocatorUtils.getElement(locator_file,"sales_tab");
//            Constants.doClick(sales_tab);
//            Thread.sleep(2000);
            WebElement sales_report=LocatorUtils.getElement(locator_file,"sales_report_tab");
            Constants.doClick(sales_report);
            WebElement financial_year_dropdown=LocatorUtils.getElement(locator_file,"financial_year_dropdown");
            Constants.doClick(financial_year_dropdown);
            WebElement financial_year=LocatorUtils.getElement(locator_file,"financial_year_select");
            Constants.doClick(financial_year);
            WebElement report_type_dropdown=LocatorUtils.getElement(locator_file,"report_type_dropdown");
            Constants.doClick(report_type_dropdown);
            WebElement report_type_select=LocatorUtils.getElement(locator_file,"report_type_select");
            Constants.doClick(report_type_select);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void click_advance_search(){
        try{
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-9000)"," ");
            WebElement advance_search_btn=LocatorUtils.getElement(locator_file,"advance_search_btn");
            Constants.doClick(advance_search_btn);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void advance_search_filters(){
        try{
            Thread.sleep(1000);
            WebElement payment_mode_select=LocatorUtils.getElement(locator_file,"payment_mode_select");
            Constants.doClick(payment_mode_select);
            WebElement payment_mode_option=LocatorUtils.getElement(locator_file,"payment_mode_option");
            Constants.doClick(payment_mode_option);
            WebElement quarter_select=LocatorUtils.getElement(locator_file,"quarter_select_dropdown");
            Constants.doClick(quarter_select);
            WebElement quarter_select_option=LocatorUtils.getElement(locator_file,"quarter_select_option");
            Constants.doClick(quarter_select_option);
            WebElement counsellor_select_dropdown=LocatorUtils.getElement(locator_file,"counsellor_select_dropdown");
            Constants.doClick(counsellor_select_dropdown);
            WebElement counsellor_select_option=LocatorUtils.getElement(locator_file,"counsellor_select_option");
            Constants.doClick(counsellor_select_option);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void click_submit(){
        try{
            WebElement submit=LocatorUtils.getElement(locator_file,"submit_report_btn");
            Constants.doClick(submit);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void report_displayed(){
        try{

            JavascriptExecutor js=(JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,700)"," ");
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0,-1200)"," ");
            WebElement download_report=LocatorUtils.getElement(locator_file,"export_report");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", download_report);
            Constants.doClick(download_report);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
