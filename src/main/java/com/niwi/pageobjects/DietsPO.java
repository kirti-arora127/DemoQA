package com.niwi.pageobjects;

import com.niwi.testbase.TestBase;
import com.niwi.utils.Constants;
import com.niwi.utils.LocatorUtils;
import com.niwi.utils.ReadFiles;
import com.niwi.utils.WaitUtils;
import io.cucumber.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DietsPO {

    String locator_file = "creatediet_locators.properties";
    String data_file="creatediet_data.properties";
    WebDriver driver;
    private List<Map<String, String>> nutrientValuesBeforeDietList = new ArrayList<>();

    public DietsPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void click_diets() {
        try {
            WebElement diets = LocatorUtils.getElement(locator_file, "diet_tab");
            Constants.doClick(diets);
            WebElement view_diet = LocatorUtils.getElement(locator_file, "view_diet_button");
            Constants.doClick(view_diet);
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click_add_diet() {
        try {

            List<WebElement> client_names=LocatorUtils.getElements(locator_file,"client_name_list");
            List<WebElement> options=LocatorUtils.getElements(locator_file,"three_dots");
            List<WebElement> trash_option=LocatorUtils.getElements(locator_file,"trash_option");
            for(int i=0;i< client_names.size();i++){
                System.out.println(client_names.get(i).getText());
                if(client_names.get(i).getText().equalsIgnoreCase("MeenuTest")){
                    System.out.println(client_names.get(i).getText());
                        options.get(i).click();
                        trash_option.get(i).click();
                        WebElement modal=LocatorUtils.getElement(locator_file,"modal_locator");
                        driver.switchTo().activeElement();
                        WebElement yes=LocatorUtils.getElement(locator_file,"yes_btn");
                        Constants.doClick(yes);

                    break;
                }
            }
            WebElement add_diet_btn = LocatorUtils.getElement(locator_file, "add_diet_button");
            Constants.doClick(add_diet_btn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public void click_add_diet_view_diet(){
//        try{ List<WebElement> client_names=LocatorUtils.getElements(locator_file,"client_name_list");
//            List<WebElement> options=LocatorUtils.getElements(locator_file,"three_dots");
//            List<WebElement> trash_option=LocatorUtils.getElements(locator_file,"trash_option");
//            for(int i=0;i< client_names.size();i++){
//                System.out.println(client_names.get(i).getText());
//                if(client_names.get(i).getText().equalsIgnoreCase("MeenuTest")){
//                    System.out.println(client_names.get(i).getText());
//                    options.get(i).click();
//                    trash_option.get(i).click();
//                    WebElement modal=LocatorUtils.getElement(locator_file,"modal_locator");
//                    driver.switchTo().activeElement();
//                    WebElement yes=LocatorUtils.getElement(locator_file,"yes_btn");
//                    Constants.doClick(yes);
//
//                    break;
//                }
//            }
//            WebElement add_diet_btn = LocatorUtils.getElement(locator_file, "add_diet_button");
//            Constants.doClick(add_diet_btn);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        }
//
//

    public void show_status() {
        try {
            WebElement status = LocatorUtils.getElement(locator_file, "status");
            Select sel = new Select(status);
            sel.selectByVisibleText("All Clients");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void select_client() {
        try {
            WebElement client_dropdown = LocatorUtils.getElement(locator_file, "client_dropdown");
            Constants.doClick(client_dropdown);
            Thread.sleep(2000);
            WebElement select_client = LocatorUtils.getElement(locator_file, "select_client");
            Constants.doClick(select_client);
            System.out.println(select_client.getText());
            Thread.sleep(1000);
            WebElement client_info = LocatorUtils.getElement(locator_file, "client_into");
            Constants.doClick(client_info);
            WebElement close_btn = LocatorUtils.getElement(locator_file, "close_btn");
            Constants.doClick(close_btn);
            WebElement click_profile = LocatorUtils.getElement(locator_file, "client_profile");
            Constants.doClick(click_profile);
            WebElement close_btn1 = LocatorUtils.getElement(locator_file, "close_btn");
            Constants.doClick(close_btn1);
            WebElement click_notes = LocatorUtils.getElement(locator_file, "client_notes");
            Constants.doClick(click_notes);
            WebElement close_btn2 = LocatorUtils.getElement(locator_file, "close_btn");
            Constants.doClick(close_btn2);
            WebElement click_feedback = LocatorUtils.getElement(locator_file, "client_feedback");
            Constants.doClick(click_feedback);
            WebElement close_btn3 = LocatorUtils.getElement(locator_file, "close_btn");
            Constants.doClick(close_btn3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void client_weight() {
        try {
            WebElement client_weight = LocatorUtils.getElement(locator_file, "client_weight");
            Constants.enterValues(client_weight, "67");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 public void diet_duration() {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

     try {// Wait for the visible 'Diet From' input field (readonly)
         WebElement dietFromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@readonly='readonly' and @type='text']")));
         dietFromInput.click();  // Trigger the calendar popup

// Wait for the calendar to appear and ensure it is fully loaded
         WebElement calendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'flatpickr-calendar') and contains(@class, 'open')]")));

// Select the desired date from the calendar (e.g., January 25, 2025)
// Modify XPath according to your date's actual format. Example for selecting a date dynamically
         WebElement dateToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@aria-label, 'January 25, 2025')]")));

         System.out.println("Attempting to select date: January 25, 2025");

         if (dateToSelect != null) {
             // Print the date's aria-label
             System.out.println("Date found in calendar: " + dateToSelect.getAttribute("aria-label"));

             // Using JavaScript to click the date (if the standard click is not working)
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateToSelect);
         } else {
             System.out.println("Could not find the date: January 25, 2025");
         }

// After selecting the date, the hidden input field should be updated with the selected date.
         WebElement hiddenDateInput = driver.findElement(By.xpath("//input[@type='hidden' and @formcontrolname='startPrescription']"));
         String selectedDate = hiddenDateInput.getAttribute("value");
         System.out.println("Selected Date from Hidden Input: " + selectedDate);

// If the selected date format doesn't match the expected format for the 'Diet From' field, convert it
// Example: Convert date from yyyy-MM-dd to dd-MM-yyyy
         SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format of the hidden input
         SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy"); // Desired format for the visible input field

         Date parsedDate = inputFormat.parse(selectedDate); // Parse the date from hidden field
         String formattedDate = outputFormat.format(parsedDate); // Format the date to the desired format

         System.out.println("Formatted Date for 'Diet From' field: " + formattedDate);

// Now, ensure the visible 'Diet From' field is updated.
// Since it's readonly, use JavaScript to set the value of the field.
         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].value = arguments[1];", dietFromInput, formattedDate);

// Trigger the 'change' event to simulate user interaction
         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].dispatchEvent(new Event('change'));", dietFromInput);

// Trigger the 'blur' event to simulate the field losing focus
         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].dispatchEvent(new Event('blur'));", dietFromInput);

// Wait for the visible input field to reflect the selected date
         wait.until(ExpectedConditions.attributeToBe(dietFromInput, "value", formattedDate));

// Verify the selected date in both fields
         System.out.println("Selected date in hidden input: " + hiddenDateInput.getAttribute("value"));
         System.out.println("Selected date in visible 'Diet From' field: " + dietFromInput.getAttribute("value"));
     }catch (Exception e) {
         e.printStackTrace();
     }
 }
    public void select_options(){
        try{
            WebElement show_nutrients=LocatorUtils.getElement(locator_file,"show_nutrient_option");
            WebElement show_mealtime=LocatorUtils.getElement(locator_file,"show_mealtime_option");
            WebElement show_foodweight=LocatorUtils.getElement(locator_file,"show_foodweight_option");
            WebElement seek_feedback=LocatorUtils.getElement(locator_file,"seek_feedback_option");
            if(show_nutrients.isSelected() && show_mealtime.isSelected() && show_foodweight.isSelected() && seek_feedback.isSelected())
            {
                System.out.println("Already selected");
            }
            else{
                Constants.doClick(show_nutrients);
                Constants.doClick(show_mealtime);
                Constants.doClick(show_foodweight);
                Constants.doClick(seek_feedback);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void create_diet(){
        WebElement create_btn=LocatorUtils.getElement(locator_file,"create_diet_submit");
        Constants.doClick(create_btn);
    }

    public void validate_create_diet(){
        List<WebElement> creatediet_dates=LocatorUtils.getElements(locator_file,"create_diet_dates");
        System.out.println(creatediet_dates.size());
        for(int i=0;i<creatediet_dates.size();i++){
            System.out.println(creatediet_dates.get(i).getText());
        }

    }

    public void select_dates_btn(){
        /*List<WebElement> diet_dates=LocatorUtils.getElements(locator_file,"create_diet_dates");
        for(int i=0;i<diet_dates.size();i++){
            Constants.doClick(diet_dates.get(0));
        }*/
        System.out.println("goo");

    }


    public void add_meal() {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        int formIndex=0;
        try {
            String[][] mealData = {
                    {"br", "poh","2","large bowl","Add seasonal veggies in poha."},  // Day 1 Breakfast
                    {"lu", "rice","1","medium bowl","Include more vegetables."},      // Day 1 Lunch
                    {"din", "dal","2","large bowl", "Add less masala."},              // Day 1 Dinner
                    {"br", "idli", "3","medium","Include sambhar."},              // Day 2 Breakfast
                    {"lu", "dalia","2","large katori ", "Add salad on the side."},     // Day 2 Lunch
                    {"din", "curry","2","large bowl", "Use low oil."},                // Day 2 Dinner
                    {"br", "oats","2","medium", "Add fruits for fiber."},         // Day 3 Breakfast
                    {"lu", "quinoa","2","large ", "Include broccoli."},           // Day 3 Lunch
                    {"din", "soup","2","large soup bowl", "Keep it low salt."},            // Day 3 Dinner
                    {"br", "paratha","3","pieces", "Add curd for probiotics."},   // Day 4 Breakfast
                    {"lu", "pasta","1","slice", "Add vegetables for nutrition."},// Day 4 Lunch
                    {"din", "chicken","1", "medium bowl","Add a side of vegetables."},    // Day 4 Dinner
                    {"br", "pancakes", "2","medium","Use whole wheat flour."},    // Day 5 Breakfast
                    {"lu", "salad","1","large bowl", "Include beans and nuts."},      // Day 5 Lunch
                    {"din", "lentils","1","medium bowl", "Add less spices."},          // Day 5 Dinner
                    {"br", "toast", "2","small","Use multigrain bread."},        // Day 6 Breakfast
                    {"lu", "fry","1","medium serving", "Add tofu for protein."},     // Day 6 Lunch
                    {"din", "mutton", "2","Small Plate","Add coconut milk."},  // Day 6 Dinner
                    {"br", "smoothie","1","large glass", "Include spinach and berries."}, // Day 7 Breakfast
                    {"lu", "biryani","2","large bowl", "Use brown rice."},            // Day 7 Lunch
                    {"din", "grilled chicken","2","large bowl", "Add lemon zest."}
            };

            for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                List<WebElement> dietDates = LocatorUtils.getElements(locator_file,"diet_dates");
                System.out.println("Processing day=" + dayIndex);

                 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dietDates.get(dayIndex));

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row ng-star-inserted']//div")));
                for (int mealIndex = 0; mealIndex<3; mealIndex++) {
                    // Enter meal
                    WebElement mealForms = LocatorUtils.getElement(locator_file, "meal_form");
                    Constants.doClick(mealForms);
                    Constants.enterValues(mealForms, mealData[dayIndex * 3 + mealIndex][0]);  // Meal type (br, lu, din)

                    WebElement mealType = LocatorUtils.getElement(locator_file, "enter_meal_type");
                    Constants.doClick(mealType);
                    WaitUtils.implicitWait();

                        //Enter food
                    WebElement food = LocatorUtils.getElement(locator_file, "enter_food");
                    Constants.enterValues(food, mealData[dayIndex * 3 + mealIndex][1]);  // Food (like poha, rice, dal, etc.)


                    WebElement foodType = LocatorUtils.getElement(locator_file, "enter_food_type");
                    Constants.doClick(foodType);

                    //Enter Quantity
                    WebElement qty=LocatorUtils.getElement(locator_file,"food_qty");
                    qty.clear();
                    Constants.enterValues(qty,mealData[dayIndex * 3 + mealIndex][2]);

                    //Enter unit
                    WebElement unit= LocatorUtils.getElement(locator_file,"food_unit");
                    Constants.doClick(unit);
                    Select sel=new Select(unit);
                    sel.selectByValue(mealData[dayIndex * 3 + mealIndex][3]);

                    WebElement mealNotes = LocatorUtils.getElement(locator_file, "meal_notes");
                    Constants.enterValues(mealNotes, mealData[dayIndex * 3 + mealIndex][4]);  // Notes (e.g., "Add less masala.")

                    WebElement clickPlus = LocatorUtils.getElement(locator_file, "add_meal_plus_btn");
                    Constants.doClick(clickPlus);
                    WaitUtils.implicitWait();
                    formIndex++;

                    if(formIndex==3){
                        //add delete code
                        WebElement delete_meal = LocatorUtils.getElement(locator_file, "delete_meal_btn");
                        Constants.doClick(delete_meal);
                        WebElement addRemainBtn = LocatorUtils.getElement(locator_file, "add_diet_remaining_days");
                        Constants.doClick(addRemainBtn);
                        formIndex=0;
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//    public void checkNutrientValue(){
//       List<WebElement> dietDates=LocatorUtils.getElements(locator_file,"diet_dates");
//        for(int day=0;day<dietDates.size();day++) {
//            System.out.println("Processing day="+day);
//
//        }
//
//    }
    public void add_remarks(){
        try{
            WebElement remarks_textarea=LocatorUtils.getElement(locator_file,"remarks_textarea");
            String remarks=ReadFiles.getDataFromPropertyFile(data_file,"remarks");
            Constants.enterValues(remarks_textarea,remarks);

            //adding food recepie link
            WebElement recepie_box=LocatorUtils.getElement(locator_file,"recepie_box");
            String recepie=ReadFiles.getDataFromPropertyFile(data_file,"oats_poha_recepie");
            Constants.enterValues(recepie_box,recepie);

            WebElement add_recepie_btn=LocatorUtils.getElement(locator_file,"add_recepie_btn");
            Constants.doClick(add_recepie_btn);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    public void click_approve_diet(){

        WebElement approve_btn=LocatorUtils.getElement(locator_file,"approve_btn");
        Constants.doClick(approve_btn);

    }
        public void diet_created(){
        List<WebElement> diet_created=LocatorUtils.getElements(locator_file,"new_diet_created");
            System.out.println(diet_created.get(0).getText());
            if(diet_created.get(0).getText().equals("MeenuTest"))
            {
                System.out.println("Diet for MeenuTest client created");
            }
            WebElement client_diet_details=LocatorUtils.getElement(locator_file,"client_diet_details");
            Constants.doClick(client_diet_details);
            WaitUtils.implicitWait();
            Constants.doClick(client_diet_details);
            WebElement diet_expand_button=LocatorUtils.getElement(locator_file,"diet_expand_button");
            Constants.doClick(diet_expand_button);
            checkNutrientValueAfterDiet();


        }

        public void add_meal_UI(){
        try{
            List<WebElement> dietDates = LocatorUtils.getElements(locator_file, "diet_dates");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dietDates.get(0));
            addMeal("bre","Poha","Smoothie", "Add Vegetables to Poha and nuts in smoothie");
            toggleMealInfo();
            addAlternateMeal("Idli","Smoothie","Shake","Serve idli with coconut chutney and add nuts to smoothie and shake");
            copyMealfor();
            copyMealForOtherDay();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        }
        private void addMeal(String mealName,String food1,String food2, String  notes){
        WebElement meal_name=LocatorUtils.getElement(locator_file,"meal_form");
        Constants.enterValues(meal_name,mealName);
        WebElement meal_type=LocatorUtils.getElement(locator_file,"enter_meal_type");
        Constants.doClick(meal_type);
        enterFood(food1);
        attachPrimFoodRecepie(food1);
        WebElement inline_plus=LocatorUtils.getElement(locator_file,"inline_add_meal");
        Constants.doClick(inline_plus);
        enterFood(food2);
        attachFoodLink(food2);
        WebElement meal_notes=LocatorUtils.getElement(locator_file,"meal_notes");
        Constants.enterValues(meal_notes,notes);

        }
        private void attachPrimFoodRecepie(String food){
        try {
            WebElement food_link = LocatorUtils.getElement(locator_file, "attach_food_recepie");
            Constants.doClick(food_link);
                WebElement select_prim_recepie = LocatorUtils.getElement(locator_file, "select_prim_recepie_option");
                Constants.doClick(select_prim_recepie);
            WebElement select_prim_food = LocatorUtils.getElement(locator_file, "select_prim_food");
            Constants.doClick(select_prim_food);
                WebElement save_recepie_link = LocatorUtils.getElement(locator_file, "save_recepie_link");
                Constants.doClick(save_recepie_link);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        }
        private void attachFoodLink(String food){
        try {
            WebElement food_link = LocatorUtils.getElement(locator_file, "attach_food_recepie");
            Constants.doClick(food_link);
            WebElement enter_food_recepie_link = LocatorUtils.getElement(locator_file, "enter_food_recepie_link");
            Constants.doClick(enter_food_recepie_link);
                WebElement recepie_link_input = LocatorUtils.getElement(locator_file, "recepie_link_input");
                String smoothie_link=ReadFiles.getDataFromPropertyFile(data_file,"smoothie_recepie_link");
                Constants.enterValues(recepie_link_input,smoothie_link);
                WebElement save_recepie_link = LocatorUtils.getElement(locator_file, "save_recepie_link");
                Constants.doClick(save_recepie_link);
           /* Constants.clickElement(locator_file,"attach_food_recepie");
            Constants.clickElement(locator_file,"enter_food_recepie_link");
            String smoothie_link=ReadFiles.getDataFromPropertyFile(data_file,"smoothie_recepie_link");
            Constants.enterValue(locator_file,"recepie_link_input",smoothie_link);
            Constants.clickElement(locator_file,"save_recepie_link");*/



        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
        private void enterFood(String food){
            WebElement enter_food=LocatorUtils.getElement(locator_file,"enter_food");
            Constants.enterValues(enter_food,food);
            WebElement food_type=LocatorUtils.getElement(locator_file,"enter_food_type");
            Constants.doClick(food_type);

        }

        private void toggleMealInfo() throws InterruptedException{
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-200)"," ");
        WebElement collapse=LocatorUtils.getElement(locator_file,"collapse_btn");
        Constants.doClick(collapse);
        WebElement meal_info=LocatorUtils.getElement(locator_file,"meal_info");
        Constants.doClick(meal_info);
        WebElement expand=LocatorUtils.getElement(locator_file,"expand_btn");
        Constants.doClick(expand);

        }
        private void addAlternateMeal(String mainFood,String altfood1,String altfood2,String notes){
        WebElement or_btn=LocatorUtils.getElement(locator_file,"or_btn");
        Constants.doClick(or_btn);
        enterFood(mainFood);
        WebElement inline_plus=LocatorUtils.getElement(locator_file,"inline_add_meal");
        Constants.doClick(inline_plus);
        enterFood(altfood1);
        WebElement or_inline_btn=LocatorUtils.getElement(locator_file,"or_inline_meal");
        Constants.doClick(or_inline_btn);
        enterFood(altfood2);

        WebElement meal_notes=LocatorUtils.getElement(locator_file,"meal_notes");
        Constants.enterValues(meal_notes,notes);

        }
        private void copyMealfor(){
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-700)", "");
        WebElement copy_meal_dinner=LocatorUtils.getElement(locator_file,"copy_and_duplicate_meal");
        Constants.doClick(copy_meal_dinner);
        WebElement meal_name_dinner = LocatorUtils.getElement(locator_file, "meal_form");
        Constants.doClick(meal_name_dinner);
        Constants.enterValues(meal_name_dinner, "din");
        WebElement meal_type_dinner = LocatorUtils.getElement(locator_file, "enter_meal_type");
        Constants.doClick(meal_type_dinner);

        }
        private void copyMealForOtherDay(){
        WebElement scroll_element=LocatorUtils.getElement(locator_file,"top_meal_form");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",scroll_element);
        WebElement copy_meal=LocatorUtils.getElement(locator_file,"copy_meal_new_day");
        Constants.doClick(copy_meal);
        WebElement sel_all_dates=LocatorUtils.getElement(locator_file,"sel_all_dates_copy");
        Constants.doClick(sel_all_dates);
        WebElement copy=LocatorUtils.getElement(locator_file,"copy_btn");
        Constants.doClick(copy);

    }




    public void master_prescribed_diet() {
        try {

              for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                  String[] client_name={"soft","veg","test","due diet2","Sales Test 2","test client","test"};
                  List<WebElement> diet_dates = LocatorUtils.getElements(locator_file, "diet_dates");
                  System.out.println("No. of dates= "+diet_dates.get(dayIndex).getText());
               System.out.println("Processing Day= " + (dayIndex+1));
                  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", diet_dates.get(dayIndex));
                    if(dayIndex<=2){
                        addMasterDiet(client_name[dayIndex]);
                    }
                    else {
                        addPrescribedDiet(client_name[dayIndex]);
                    }
                  System.out.println("Nutrient values during diet creation");
                    checkNutrientValueDuringDiet();
                  WebElement add_diet_remain_days=LocatorUtils.getElement(locator_file,"add_diet_remaining_days");
                  Constants.doClick(add_diet_remain_days);

           }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void checkNutrientValueDuringDiet(){
        String[] nutrients = {"energy", "fat", "carbs", "protein", "fiber"};
        Map<String, String> dailyNutrientValues = new HashMap<>();
        for (String nutrient : nutrients) {
            WebElement value = LocatorUtils.getElement(locator_file, "nutrient_"+nutrient+"_value");
            String nutrientValue=value.getText().trim();
            dailyNutrientValues.put(nutrient,nutrientValue);
            System.out.println(nutrient.substring(0, 1).toUpperCase() + nutrient.substring(1) + "=" + nutrientValue);
        }
        nutrientValuesBeforeDietList.add(dailyNutrientValues);
    }

    private void checkNutrientValueAfterDiet() {
        try {
            List<WebElement> view_diet_size = driver.findElements(By.xpath("//div[@class='diet_detail_header']//div[contains(@class,'meal_details')]"));
            System.out.println("Nutrient values after diet creation");
            String[] nutrients = {"energy", "fat", "carbs", "protein", "fiber"};

            for (int i = 0; i < view_diet_size.size(); i++) {
                System.out.println("\nProcessing Diet " + (i + 1));
                Map<String, String> beforeDietValues = (i < nutrientValuesBeforeDietList.size())
                        ? nutrientValuesBeforeDietList.get(i)
                        : new HashMap<>(); // Default empty map if no matching before-diet value exists

                for (String nutrient : nutrients) {
                    List<WebElement> value = LocatorUtils.getElements(locator_file, "nutrient_" + nutrient + "_value_view_diet");
                    String afterValue = value.get(i).getText().replaceAll("\\s+", " ").trim();

                    // Compare with before diet values
                    String beforeValue = beforeDietValues.getOrDefault(nutrient, "N/A").replaceAll("\\s+", " ").trim();

                    // Print comparison result
                    System.out.println(nutrient.substring(0, 1).toUpperCase() + nutrient.substring(1) + " Before: " + beforeValue + ", After: " + afterValue);

                     //Optional: Assert if values are expected to be the same
                    if (!beforeValue.equals("N/A") && !beforeValue.equals(afterValue)) {
                        System.out.println("Mismatch found for " + nutrient + ": Before = " + beforeValue + ", After = " + afterValue);
                    }
                    else {
                        System.out.println("Values are same ");
                    }

                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

        private void addMasterDiet(String client_diet){

            WebElement search_arrow=LocatorUtils.getElement(locator_file,"search_arrow");
            Constants.doClick(search_arrow);
            WebElement search_input=LocatorUtils.getElement(locator_file,"search_box_input");
           WaitUtils.implicitWait();
            Constants.enterValues(search_input,client_diet);
            WebElement search_input_btn=LocatorUtils.getElement(locator_file,"search_input_btn");
            WaitUtils.implicitWait();
            Constants.doClick(search_input_btn);
            List<WebElement> client_master_diets=LocatorUtils.getElements(locator_file,"client_master_diets");
            Constants.doClick(client_master_diets.get(0));
        }
        private void addPrescribedDiet(String client_diet){
            WebElement search_arrow=LocatorUtils.getElement(locator_file,"search_arrow");
            Constants.doClick(search_arrow);
            WebElement presc_btn=LocatorUtils.getElement(locator_file,"presc_diet_btn");
            Constants.doClick(presc_btn);
            WebElement search_input=LocatorUtils.getElement(locator_file,"search_box_input");
            Constants.enterValues(search_input,client_diet);
            WebElement search_input_btn=LocatorUtils.getElement(locator_file,"search_input_btn");
            Constants.doClick(search_input_btn);
            List<WebElement> client_prescribed_diets=LocatorUtils.getElements(locator_file,"client_master_diets");
            Constants.doClick(client_prescribed_diets.get(0));
        }




       /* public void changeDate(){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
           /* // Locate the hidden input field that stores the actual date
            WebElement hiddenDateInput = driver.findElement(By.xpath("//input[@formcontrolname='dietPrescriptionDate']"));

            // Use JavaScript to set a new date in the hidden field

            js.executeScript("arguments[0].setAttribute('value', '2025-02-15');", hiddenDateInput);

            // Dispatch an Angular change event
            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", hiddenDateInput);

            // Locate and click the readonly input field to open the date picker
            WebElement readonlyDateInput = driver.findElement(By.xpath("//input[@type='text' and @readonly]"));
            readonlyDateInput.click(); // Opens the calendar

            // Select the new date from the date picker (Adjust XPath based on UI)
            WebElement dateElement = driver.findElement(By.xpath("//span[text()='15']")); // Change based on UI
            dateElement.click(); // Clicks the date

            // Validate the date is updated
            System.out.println("Updated Date: " + readonlyDateInput.getAttribute("value"));



            // Locate the hidden input field that stores the actual date
            WebElement hiddenDateInput1= driver.findElement(By.xpath("//input[@formcontrolname='endPrescription']"));

            // Use JavaScript to set a new date in the hidden field

            js.executeScript("arguments[0].setAttribute('value', '2025-02-7');", hiddenDateInput1);

            // Dispatch an Angular change event
            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", hiddenDateInput1);

            // Locate and click the readonly input field to open the date picker
            WebElement readonlyDateInput1 = driver.findElement(By.xpath("//label[contains(text(),'Diet To')]/following::input[@type='text' and @readonly][1]"));
            readonlyDateInput1.click(); // Opens the calendar

            // Select the new date from the date picker (Adjust XPath based on UI)
            WebElement dateElement1 = driver.findElement(By.xpath("//span[text()='7']")); // Change based on UI
            dateElement1.click(); // Clicks the date

            // Validate the date is updated
            System.out.println("Updated Date: " + readonlyDateInput1.getAttribute("value"));


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }*/


    public void checkClientDietDetails(){
        try{
            List<WebElement> diet_created=LocatorUtils.getElements(locator_file,"new_diet_created");
            System.out.println(diet_created.get(0).getText());
            if(diet_created.get(0).getText().equals("MeenuTest"))
            {
                System.out.println("Diet for MeenuTest client created");
            }
            WebElement client_diet_details=LocatorUtils.getElement(locator_file,"client_diet_details");
            Constants.doClick(client_diet_details);
            WaitUtils.implicitWait();
            Constants.doClick(client_diet_details);
            WebElement diet_expand_button=LocatorUtils.getElement(locator_file,"diet_expand_button");
            Constants.doClick(diet_expand_button);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void optionsViewDiet(){
        try{

            view_diet();
            Thread.sleep(4000);
            edit_diet();
            Thread.sleep(4000);
            download_diet();
            Thread.sleep(4000);
            copy_diet();
            Thread.sleep(4000);
            delete_diet();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void edit_diet(){
        WebElement three_dots=LocatorUtils.getElement(locator_file,"three_dots");
        Constants.doClick(three_dots);
        WebElement edit_option=LocatorUtils.getElement(locator_file,"edit_diet_option");
        Constants.doClick(edit_option);
        WebElement remarks=LocatorUtils.getElement(locator_file,"remarks_textarea");
        remarks.clear();
        remarks.sendKeys("Very good diet prescribed");
        WebElement approve_btn=LocatorUtils.getElement(locator_file,"approve_btn");
        Constants.doClick(approve_btn);

            }

    private void view_diet(){
        try {
            WebElement three_dots = LocatorUtils.getElement(locator_file, "three_dots");
            Constants.doClick(three_dots);
            WebElement view_as = LocatorUtils.getElement(locator_file, "view_as_option");
            Constants.doClick(view_as);
            Thread.sleep(2000);
            driver.navigate().back();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    private void copy_diet() {
        try {
            WebElement three_dots = LocatorUtils.getElement(locator_file, "three_dots");
            Constants.doClick(three_dots);
            WebElement copy_diet = LocatorUtils.getElement(locator_file, "copy_diet_option");
            Constants.doClick(copy_diet);
            WebElement copy_client_dropdown = LocatorUtils.getElement(locator_file, "client_dropdown");
            Constants.doClick(copy_client_dropdown);
            WebElement select_copy_client = LocatorUtils.getElement(locator_file, "select_copy_client");
            Constants.doClick(select_copy_client);
            List<WebElement> dietDates = LocatorUtils.getElements(locator_file, "diet_dates");
            System.out.println(dietDates.size());
            WebElement select_all_btn = LocatorUtils.getElement(locator_file, "select_all_dates");
            Constants.doClick(select_all_btn);
            WebElement approve_btn = LocatorUtils.getElement(locator_file, "approve_btn");
            Constants.doClick(approve_btn);
            Thread.sleep(2000);
            driver.navigate().back();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void download_diet() {
        WebElement three_dots=LocatorUtils.getElement(locator_file,"three_dots");
        Constants.doClick(three_dots);
        WebElement download_diet=LocatorUtils.getElement(locator_file,"download_diet_option");
        Constants.doClick(download_diet);
        System.out.println("Diet Downloaded");
    }
    private void delete_diet () {
        WebElement three_dots = LocatorUtils.getElement(locator_file, "three_dots");
        Constants.doClick(three_dots);
        WebElement delete_diet=LocatorUtils.getElement(locator_file,"trash_option");
        Constants.doClick(delete_diet);
        System.out.println("Diet deleted");
    }





    }






