package com.niwi.pageobjects;

import com.niwi.utils.Constants;
import com.niwi.utils.LocatorUtils;
import com.niwi.utils.WaitUtils;
import jdk.jfr.Threshold;
import org.bson.types.BSONTimestamp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.*;

public class ClientsPO {
    WebDriver driver;

    String locator_file = "clients_locators.properties";

    public ClientsPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Map<String, String> form_data = new LinkedHashMap<>();

    public void click_client_tab() {
        try {
            WebElement clients_tab = LocatorUtils.getElement(locator_file, "clients_tab");
            Constants.doClick(clients_tab);
            WebElement client_details = WaitUtils.getWait(driver, 10).until(ExpectedConditions.elementToBeClickable(LocatorUtils.getElement(locator_file, "client_details")));
            Constants.doClick(client_details);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add_new_client() {
        try {

            WebElement add_client_btn = LocatorUtils.getElement(locator_file, "add_client_btn");
            Constants.doClick(add_client_btn);
            WebElement firstname = LocatorUtils.getElement(locator_file, "client_firstname");
            Constants.enterValues(firstname, Constants.enterDynamicData("name"));
            WebElement email = LocatorUtils.getElement(locator_file, "client_email");
            Constants.enterValues(email, Constants.enterDynamicData("email_id"));
            WebElement phone = LocatorUtils.getElement(locator_file, "client_phoneno");
            Constants.enterValues(phone, Constants.enterDynamicData("phone_no"));
            WebElement state = LocatorUtils.getElement(locator_file, "state_select");
            Constants.doClick(state);
            WebElement enter_state_name = LocatorUtils.getElement(locator_file, "state_input");
            Constants.enterValues(enter_state_name, "Punjab");
            WebElement state_click = LocatorUtils.getElement(locator_file, "punjab_state_select");
            Constants.doClick(state_click);
            WebElement city = LocatorUtils.getElement(locator_file, "client_city");
            Constants.enterValues(city, "Ludhiana");
            WebElement add_btn = LocatorUtils.getElement(locator_file, "create_client_btn");
            Constants.doClick(add_btn);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generate_dynamic_onboarding() {
        try {
            WaitUtils.getWait(driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-message")));
            Thread.sleep(2000);
            WebElement three_dots = LocatorUtils.getElement(locator_file, "three_dots");
            WaitUtils.getWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(LocatorUtils.getLocator(locator_file, "three_dots")));
            //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",three_dots);
            Constants.doClick(three_dots);
            WebElement generate_form = LocatorUtils.getElement(locator_file, "new_onboarding_form");
            Constants.doClick(generate_form);
//            WebElement toast_message=LocatorUtils.getElement(locator_file,"toast_message");
//            String data=toast_message.getText();
//            System.out.println(data);
            choose_form_fields_onboarding();


//           //edit generated onboarding form
//            edit_onboarding_form();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void choose_form_fields_onboarding() {
        try {
            WebElement modal = WaitUtils.getWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.getLocator(locator_file, "onboarding_modal")));
            driver.switchTo().activeElement();
            WebElement click_to_choose = LocatorUtils.getElement(locator_file, "click_to_select_forms");
            WaitUtils.getWait(driver, 2).until(ExpectedConditions.elementToBeClickable(LocatorUtils.getLocator(locator_file, "click_to_select_forms")));
            Constants.doClick(click_to_choose);
            WebElement contact_form = LocatorUtils.getElement(locator_file, "contact_details_form");
            Constants.doClick(contact_form);
            Constants.doClick(click_to_choose);
            WebElement health_form = LocatorUtils.getElement(locator_file, "health_details_form");
            Constants.doClick(health_form);
            WebElement send_btn = LocatorUtils.getElement(locator_file, "send_onboarding_btn");
            Constants.doClick(send_btn);
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //fill generated form sections
    public void fill_dynamic_onboarding_form() {
        try {
            WaitUtils.getWait(driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-message")));
            WebElement form_button = WaitUtils.getWait(driver, 10).until(ExpectedConditions.elementToBeClickable(LocatorUtils.getElement(locator_file, "onboarding_form_btn")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", form_button);
            Thread.sleep(500);
            Constants.doClick(form_button);
            WaitUtils.getWait(driver, 20);
            Set<String> allids = driver.getWindowHandles();
            for (String bothids : allids) {
                System.out.println("Both window ids= " + bothids);
            }
            ArrayList<String> ls = new ArrayList<>(allids);
            driver.switchTo().window(ls.get(1));

            WaitUtils.getWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(LocatorUtils.getLocator(locator_file, "onboarding_form_load")));
            Thread.sleep(500);
            contact_details();
            Thread.sleep(500);
            health_Details();
            Thread.sleep(500);
            WebElement submit_btn = driver.findElement(By.cssSelector("ion-col.actionBtn"))
                    .findElement(By.cssSelector("ion-button"))
                    .getShadowRoot()
                    .findElement(By.cssSelector("button.button-native"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit_btn);
            WaitUtils.getWait(driver, 10);
            driver.close();
            driver.switchTo().window(ls.get(0));
            driver.navigate().refresh();
            view_response();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //to fill full form
    public void fill_onboarding() {
        try {


            System.out.println(driver.getWindowHandle());
            WebElement form_button = WaitUtils.getWait(driver, 10).until(ExpectedConditions.elementToBeClickable(LocatorUtils.getElement(locator_file, "onboarding_form_btn")));
            Constants.doClick(form_button);
            WaitUtils.getWait(driver, 20);
            Set<String> allids = driver.getWindowHandles();
            for (String bothids : allids) {
                System.out.println("Both window ids= " + bothids);
            }
            ArrayList<String> ls = new ArrayList<>(allids);
            driver.switchTo().window(ls.get(1));

            WaitUtils.getWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(LocatorUtils.getLocator(locator_file, "onboarding_form_load")));

            personal_details();
            contact_details();
            health_Details();
            diet_details();
            other_details();

            //submit button

            WebElement submit_btn = driver.findElement(By.cssSelector("ion-col.actionBtn"))
                    .findElement(By.cssSelector("ion-button"))
                    .getShadowRoot()
                    .findElement(By.cssSelector("button.button-native"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit_btn);

            WaitUtils.getWait(driver, 10);

            for (Map.Entry<String, String> entry : form_data.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }


            driver.close();
            driver.switchTo().window(ls.get(0));
            driver.navigate().refresh();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void view_response() {
        try {

            WebElement onboarding_tab = LocatorUtils.getElement(locator_file, "onboarding_form_tab");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", onboarding_tab);
            Thread.sleep(500);
            Constants.doClick(onboarding_tab);
            WebElement response = WaitUtils.getWait(driver, 20)
                    .until(ExpectedConditions.elementToBeClickable(LocatorUtils.getLocator(locator_file, "onboarding_form_response")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", response);
            Thread.sleep(500);
            Constants.doClick(response);
            WebElement view = LocatorUtils.getElement(locator_file, "view_response_btn");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'})", view);
            Constants.doClick(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validate_onboarding() {
        try {
            Map<String, String> response_table = new LinkedHashMap<>();
            //view_response();
            List<WebElement> response_sections = LocatorUtils.getElements(locator_file, "response_sections");
            System.out.println("*********************View Responses******************");
            WaitUtils.getWait(driver, 20);
            for (WebElement responses : response_sections) {
                List<WebElement> divs = responses.findElements(By.tagName("div"));
                if (divs.size() >= 2) {
                    Thread.sleep(500);
                    String label = divs.get(0).getText().trim();
                    String value = divs.get(1).getText().trim();
                    response_table.put(label, value);
                }

            }

            for (Map.Entry<String, String> entry : response_table.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            System.out.println("\n********** Comparing Form vs Response **********");

            for (Map.Entry<String, String> entry : form_data.entrySet()) {
                String key = entry.getKey();
                String expectedValue = entry.getValue();
                String actualValue = response_table.getOrDefault(key, "Not Found");

                if (expectedValue.equalsIgnoreCase(actualValue)) {
                    System.out.println("✅ Match: " + key + " = " + expectedValue);
                } else {
                    System.out.println("❌ Mismatch: " + key + " => Expected: " + expectedValue + ", Found: " + actualValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void personal_details() {
        try {
            WebElement select_gender = WaitUtils.getWait(driver, 40).until(ExpectedConditions.elementToBeClickable(LocatorUtils.getElement(locator_file, "select_gender")));
            Constants.doClick(select_gender);
            Select gender = new Select(select_gender);
            gender.selectByValue("male");
            form_data.put("Gender", "male");

            WebElement dob = LocatorUtils.getElement(locator_file, "select_dob");
            Constants.doClick(dob);


            WebElement shadow_dom = LocatorUtils.getElement(locator_file, "shadow_modal_xpath")
                    .findElement(LocatorUtils.getLocator(locator_file, "datetime_component")).getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "datetime_icon_item"))
                    .findElement(LocatorUtils.getLocator(locator_file, "datetime_icon")).getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "icon_inner"))
                    .findElement(LocatorUtils.getLocator(locator_file, "icon_svg"));
            Thread.sleep(500);
            shadow_dom.click();


            WebElement monthSelect = LocatorUtils.getElement(locator_file, "datetime_component")
                    .getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "picker_internal"))
                    .findElement(LocatorUtils.getLocator(locator_file, "month_column"))
                    .getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "month_button"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", monthSelect);
            monthSelect.click();

            WaitUtils.getWait(driver, 20);

            WebElement yearSelect = LocatorUtils.getElement(locator_file, "datetime_component")
                    .getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "picker_internal"))
                    .findElement(LocatorUtils.getLocator(locator_file, "year_column"))
                    .getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "year_button"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearSelect);
            WaitUtils.getWait(driver, 10);
            yearSelect.click();
            WebElement open_calendar = LocatorUtils.getElement(locator_file, "datetime_component")
                    .getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "calendar_open_ion"))
                    .findElement(LocatorUtils.getLocator(locator_file, "calendar_open_label"));
            open_calendar.click();

            WebElement dateSelect = LocatorUtils.getElement(locator_file, "datetime_component")
                    .getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "calendar_month"))
                    .findElement(LocatorUtils.getLocator(locator_file, "calendar_grid"))
                    .findElement(LocatorUtils.getLocator(locator_file, "calendar_day_button"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dateSelect);
            Thread.sleep(500); // optional: allow any animation to complete

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateSelect);


            WebElement done_button = LocatorUtils.getElement(locator_file, "datetime_component")
                    .getShadowRoot()
                    .findElement(LocatorUtils.getLocator(locator_file, "datetime_footer"))
                    .findElement(LocatorUtils.getLocator(locator_file, "datetime_action_buttons"))
                    .findElement(LocatorUtils.getLocator(locator_file, "datetime_buttons"))
                    .findElement(LocatorUtils.getLocator(locator_file, "done_button"));

            done_button.click();
            form_data.put("Date of Birth", "04-04-2008");


            //height - weight - occupation
            WebElement height_unit = LocatorUtils.getElement(locator_file, "select_height_unit");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", height_unit);
            Select height = new Select(height_unit);
            height.selectByValue("Feet/inches");
            WebElement height_value = LocatorUtils.getElement(locator_file, "input_height");
            Constants.enterValues(height_value, "5.6");
            form_data.put("Height", "5.6 Feet/inches");


            WebElement weight_unit = LocatorUtils.getElement(locator_file, "select_weight_unit");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weight_unit);
            Select weight = new Select(weight_unit);
            weight.selectByValue("kgs");
            WebElement weight_value = LocatorUtils.getElement(locator_file, "input_weight");
            Constants.enterValues(weight_value, "56");
            form_data.put("Weight", "56 kgs");

            WebElement occupation = LocatorUtils.getElement(locator_file, "input_occupation");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", occupation);
            Constants.enterValues(occupation, "Working");
            form_data.put("Occupation", "working");


            //Food Allergies
            WebElement food_allergies = LocatorUtils.getElement(locator_file, "select_food_allergies");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", food_allergies);
            Constants.doClick(food_allergies);
            //select options
            List<WebElement> checkboxes = LocatorUtils.getElements(locator_file, "food_allergies_options");
            StringBuilder selected_allergies = new StringBuilder();
            for (WebElement checkbox : checkboxes) {
                WaitUtils.getWait(driver, 10);
                String value = checkbox.getText();
                // System.out.println(value);
                if (value.equalsIgnoreCase("Milk") || value.equalsIgnoreCase("Gelatin") || value.equalsIgnoreCase("Almonds")) {
                    if (!checkbox.isSelected()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                        if (selected_allergies.length() > 0) {
                            selected_allergies.append(", ");
                        }
                        selected_allergies.append(value);
                    }
                }
            }
            form_data.put("Food Allergies", selected_allergies.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contact_details() {
        try {

            WebElement whatsapp = LocatorUtils.getElement(locator_file, "input_whatsapp");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whatsapp);
            form_data.put("Whatsapp Number", Constants.fillAndReturn(whatsapp, "phone_no"));

            WebElement email = LocatorUtils.getElement(locator_file, "input_email");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
            form_data.put("Email", Constants.fillAndReturn(email, "email_id"));


            WebElement address = LocatorUtils.getElement(locator_file, "input_address");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", address);
            form_data.put("Address", Constants.fillAndReturn(address, "number"));


            WebElement country = LocatorUtils.getElement(locator_file, "select_country");
            Select country_name = new Select(country);
            country_name.selectByVisibleText(" India ");

            form_data.put("Country", "India");

            Thread.sleep(500);
            WebElement state = LocatorUtils.getElement(locator_file, "select_state");
            Select state_name = new Select(state);
            state_name.selectByVisibleText(" Punjab ");
            form_data.put("State", "Punjab");


            WebElement city = LocatorUtils.getElement(locator_file, "input_city");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", city);
            Constants.enterValues(city, "Ludhiana");
            form_data.put("City", "Ludhiana");

            WebElement pincode = LocatorUtils.getElement(locator_file, "input_pincode");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pincode);
            Constants.enterValues(pincode, "141003");
            form_data.put("Pin Code", "141003");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void read_contact_details_from_filled_form() {
        try {
            WebElement whatsapp = LocatorUtils.getElement(locator_file, "input_whatsapp");
            form_data.put("Whatsapp Number", whatsapp.getAttribute("value"));

            WebElement email = LocatorUtils.getElement(locator_file, "input_email");
            form_data.put("Email", email.getAttribute("value"));

            WebElement address = LocatorUtils.getElement(locator_file, "input_address");
            form_data.put("Address", address.getAttribute("value"));

            WebElement country = LocatorUtils.getElement(locator_file, "select_country");
            Select countrySelect = new Select(country);
            form_data.put("Country", countrySelect.getFirstSelectedOption().getText());


            WebElement state = LocatorUtils.getElement(locator_file, "select_state");
            Select stateSelect = new Select(state);
            form_data.put("State", stateSelect.getFirstSelectedOption().getText());
            System.out.println(stateSelect.getFirstSelectedOption().getText());

            WebElement city = LocatorUtils.getElement(locator_file, "input_city");
            form_data.put("City", city.getAttribute("value"));

            WebElement pincode = LocatorUtils.getElement(locator_file, "input_pincode");
            form_data.put("Pin Code", pincode.getAttribute("value"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void health_Details() {
        try {
            WebElement blood_grp = LocatorUtils.getElement(locator_file, "select_blood_group");
            Select blood_group = new Select(blood_grp);
            blood_group.selectByVisibleText(" B+ ");
            form_data.put("Blood Group", "B+");

            WebElement reason_joining = LocatorUtils.getElement(locator_file, "select_reason_of_joining");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reason_joining);
            Constants.doClick(reason_joining);
            List<WebElement> reasons = LocatorUtils.getElements(locator_file, "joining_reasons_options");
            StringBuilder selected_reasons = new StringBuilder();
            for (WebElement joining_options : reasons) {
                WaitUtils.getWait(driver, 10);
                String reason_value = joining_options.getText();
                if (reason_value.equalsIgnoreCase("Health Issue/Disease Management") || reason_value.equalsIgnoreCase("PCOD/PCOS") || reason_value.equalsIgnoreCase("Sports Diet")) {
                    if (!joining_options.isSelected()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", joining_options);
                        if (selected_reasons.length() > 0) {
                            selected_reasons.append(", ");
                        }
                        selected_reasons.append(reason_value);
                    }

                }
            }
            form_data.put("Reason For Joining * (select all relevent options)", selected_reasons.toString());
            WebElement medicine_history = LocatorUtils.getElement(locator_file, "input_medicine_history");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", medicine_history);
            Constants.enterValues(medicine_history, "No");
            form_data.put("Any medication being taken currently * (write none, if not taking anything)", "No");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void read_health_details_from_filled_form() {
        try {
            WebElement blood_grp = LocatorUtils.getElement(locator_file, "select_blood_group");
            Select blood_group = new Select(blood_grp);
            form_data.put("Blood Group", blood_group.getFirstSelectedOption().getText().trim());

            WebElement reason_joining = LocatorUtils.getElement(locator_file, "select_reason_of_joining");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reason_joining);
            Constants.doClick(reason_joining);

            List<WebElement> selectedReasons = LocatorUtils.getElements(locator_file, "joining_reasons_options");
            StringBuilder selected = new StringBuilder();
            for (WebElement option : selectedReasons) {
                if (option.isSelected()) {
                    String reason_value = option.getText().trim();
                    if (selected.length() > 0) selected.append(", ");
                    selected.append(reason_value);
                }
            }
            form_data.put("Reason For Joining * (select all relevent options)", selected.toString());

            WebElement medicine_history = LocatorUtils.getElement(locator_file, "input_medicine_history");
            form_data.put("Any medication being taken currently * (write none, if not taking anything)", medicine_history.getAttribute("value").trim());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void
    diet_details() {
        try {
            WebElement diet_preference = LocatorUtils.getElement(locator_file, "select_dietary_preference");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", diet_preference);
            Constants.doClick(diet_preference);
            List<WebElement> diet_preferences = LocatorUtils.getElements(locator_file, "dietary_preference_options");
            StringBuilder selected_diet_prefers = new StringBuilder();
            for (WebElement diet_prefer : diet_preferences) {
                WaitUtils.getWait(driver, 10);
                String diet_value = diet_prefer.getText();
                if (diet_value.equalsIgnoreCase("Gluten Free") || diet_value.equalsIgnoreCase("Vegan") || diet_value.equalsIgnoreCase("Eggetarian")) {
                    if (!diet_prefer.isSelected()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", diet_prefer);
                        if (selected_diet_prefers.length() > 0) {
                            selected_diet_prefers.append(", ");
                        }
                        selected_diet_prefers.append(diet_value);
                    }

                }
            }
            form_data.put("Dietary Preference * (select all relevent options)", selected_diet_prefers.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void other_details() {
        try {
            WebElement social_media = LocatorUtils.getElement(locator_file, "select_social_media_platform");
            Select social = new Select(social_media);
            social.selectByVisibleText(" Instagram ");
            form_data.put("Where did you hear about us?", "Instagram");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void select_client() {
        try {
            WebElement click_dropdown = LocatorUtils.getElement(locator_file, "select_client_dropdown");
            Constants.doClick(click_dropdown);
            WebElement select_client = LocatorUtils.getElement(locator_file, "select_client");
            Constants.doClick(select_client);
            WaitUtils.getWait(driver,20);
            WebElement click_selected_client = LocatorUtils.getElement(locator_file, "click_selected_client");
            Thread.sleep(200);
            Constants.doClick(click_selected_client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void check_for_onboarding_form() {
        try {
           // WebElement form_button = WaitUtils.getWait(driver, 10).until(ExpectedConditions.elementToBeClickable(LocatorUtils.getElement(locator_file, "onboarding_form_btn")));
            WebElement form_button=LocatorUtils.getElement(locator_file,"onboarding_form_btn");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", form_button);
            Thread.sleep(500);
            if (form_button.isDisplayed()) {
                System.out.println("Form Already present ");
                edit_response();

            } else {
                generate_dynamic_onboarding();
                edit_response();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isOptionSelected(List<WebElement> selected_options, String option_text){
        for(WebElement options:selected_options){
            if(options.getText().trim().equalsIgnoreCase(option_text)){
                return true;
            }

        }
        return false;
    }

    public List<String> edit_response() {
        List<String> selectedSections=new ArrayList<>();
        try {
            WebElement onboarding_tab = LocatorUtils.getElement(locator_file, "onboarding_form_tab");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", onboarding_tab);
            Thread.sleep(500);
            Constants.doClick(onboarding_tab);
            WebElement response = WaitUtils.getWait(driver, 20)
                    .until(ExpectedConditions.elementToBeClickable(LocatorUtils.getLocator(locator_file, "onboarding_form_response")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", response);
            Thread.sleep(500);
            Constants.doClick(response);
            WebElement edit = LocatorUtils.getElement(locator_file, "edit_onboarding_btn");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'})", edit);
            Constants.doClick(edit);
            WebElement select_option = LocatorUtils.getElement(locator_file, "edit_select_options");
            Select sel = new Select(select_option);
            List<WebElement> selectedOptions = sel.getAllSelectedOptions();
            System.out.println("Already selected sections:");
            for (WebElement option : selectedOptions) {
                String text=option.getText().trim();
                selectedSections.add(text);
                System.out.println(text);
            }

            // Select new sections if not already selected
            if (!isOptionSelected(selectedOptions, "Contact Details")) {
                sel.selectByVisibleText("Contact Details");
                selectedSections.add("Contact Details");
            }
            if (!isOptionSelected(selectedOptions, "Diet Details")) {
                sel.selectByVisibleText("Diet Details");
                selectedSections.add("Diet Details");
            }
            WebElement edit_send = LocatorUtils.getElement(locator_file, "edit_send_btn");
            Constants.doClick(edit_send);
            WaitUtils.getWait(driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-message")));
            Thread.sleep(2000);
            fill_edit_dynamic_onboarding_form(selectedSections);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedSections;
    }




    public boolean isFormFilled(Map<String, String> form_data, List<String> requiredFields) {
        for (String field : requiredFields) {
            String value = form_data.get(field);
            System.out.println("Checking field: '" + field + "' → Value: '" + value + "'");
            if (value == null || value.trim().isEmpty()) {
                System.out.println("Missing or empty: " + field);
                return false;
            }
        }
        return true;
    }

    public void fill_edit_dynamic_onboarding_form(List<String> selectedSections) {
        try {
            WaitUtils.getWait(driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-message")));
            WebElement form_button = WaitUtils.getWait(driver, 10).until(ExpectedConditions.elementToBeClickable(LocatorUtils.getElement(locator_file, "onboarding_form_btn")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", form_button);
            Thread.sleep(500);
            Constants.doClick(form_button);
            WaitUtils.getWait(driver, 20);
            Set<String> allids = driver.getWindowHandles();
            for (String bothids : allids) {
                System.out.println("Both window ids= " + bothids);
            }
            ArrayList<String> ls = new ArrayList<>(allids);
            driver.switchTo().window(ls.get(1));

            WaitUtils.getWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(LocatorUtils.getLocator(locator_file, "onboarding_form_load")));


            for(String section:selectedSections ) {
                switch (section) {
                    case "Contact Details": {
                        // STEP 1: Read values into form_data
                        read_contact_details_from_filled_form();
                        System.out.println("Form data"+form_data);

                        // STEP 2: Check if form is filled based on required fields
                        List<String> contactFields = Arrays.asList("Whatsapp Number", "Email", "Address");
                        if (isFormFilled(form_data, contactFields)) {
                            System.out.println("Contact Details already filled");
                            update_contact_details();
                        } else {
                            contact_details();
                        }
                        break;
                    }
                    case "Health Details": { read_health_details_from_filled_form();
                        List<String> healthFields = Arrays.asList("Blood Group","Any medication being taken currently * (write none, if not taking anything)");
                        if (isFormFilled(form_data, healthFields)) {
                            System.out.println("Health Details already filled");
                            update_health_details();
                        } else {
                            health_Details();
                        }
                        break;
                }
                    case "Diet Details": {
                        //diet_details();
                        System.out.println("No update needed");
                        break;
                    }
                    default:
                        System.out.println("No section selected");


                }
                Thread.sleep(500);
            }



                WebElement submit_btn = driver.findElement(By.cssSelector("ion-col.actionBtn"))
                    .findElement(By.cssSelector("ion-button"))
                    .getShadowRoot()
                    .findElement(By.cssSelector("button.button-native"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit_btn);


            WaitUtils.getWait(driver, 40);
            driver.close();
            driver.switchTo().window(ls.get(0));
            driver.navigate().refresh();
            WaitUtils.getWait(driver, 10);
            select_client();
            WaitUtils.getWait(driver, 10);
            view_response();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update_contact_details() {
        WebElement whatsapp = LocatorUtils.getElement(locator_file, "input_whatsapp");
        whatsapp.clear();
        form_data.put("Whatsapp Number", Constants.fillAndReturn(whatsapp, "phone_no"));


        WebElement email = LocatorUtils.getElement(locator_file, "input_email");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
        email.clear();
        form_data.put("Email", Constants.fillAndReturn(email, "email_id"));

    }

    public void update_health_details() {
        WebElement medicine_history = LocatorUtils.getElement(locator_file, "input_medicine_history");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", medicine_history);

        medicine_history.clear();
        Constants.enterValues(medicine_history,"No");
        form_data.put("Any medication being taken currently * (write none, if not taking anything)","No");
        WaitUtils.getWait(driver,20);

    }

    public void update_diet_details(){
    System.out.println("Diet details ");
}

}
