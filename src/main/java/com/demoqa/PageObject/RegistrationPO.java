package com.demoqa.PageObject;

import com.demoqa.utils.Constants;
import com.demoqa.utils.LocatorUtils;
import com.demoqa.utils.ReadFiles;
import com.demoqa.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationPO {

    WebDriver driver;
    String locator_file="registration_Locators.properties";
    String data_file="Data.properties";

    String firstName = "";
    String lastName = "";
    String email = "";
    String genderF = "";
    String contact = "";
    String dob = "";
    String subjects1 = "";
    String hobbies1 = "";
    String address = "";

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

public void fill_form() throws InterruptedException {
    WebElement first_name = LocatorUtils.getElement(locator_file, "first_name");
    Constants.enterValues(first_name, "Jack");
    firstName = first_name.getAttribute("value");

    WebElement last_name = LocatorUtils.getElement(locator_file, "last_name");
    Constants.enterValues(last_name, "Danielle");
    lastName = last_name.getAttribute("value");

    WebElement UserEmail = LocatorUtils.getElement(locator_file, "UserEmail");
    Constants.enterValues(UserEmail, "email34@mailinator.com");
    email = UserEmail.getAttribute("value");
    WebElement gender = LocatorUtils.getElement(locator_file, "Female");
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gender);
    //  Constants.doClick(gender);
    genderF = gender.getText();

    WebElement mobile = LocatorUtils.getElement(locator_file, "mobile");
    Constants.enterValues(mobile, "8876544566");
    contact = mobile.getAttribute("value");
    WebElement subjectInput = LocatorUtils.getElement(locator_file, "subject");
    String[] subjects = {"Computer Science", "Maths"};
    for (String s : subjects) {
        subjectInput.sendKeys(s);
        subjectInput.sendKeys(Keys.ENTER);
    }
    subjects1 = "Computer Science, Maths";

    //WebElement uploadpicture = LocatorUtils.getElement(locator_file, "uploadpicture");
   // String pic = ReadFiles.getDataFromPropertyFile(data_file, "pic");
 //   Constants.enterValues(uploadpicture, pic);

    WebElement address1 = LocatorUtils.getElement(locator_file, "address");
    Constants.enterValues(address1, "H. NO. 45, GANGA NAGAR");
    address= address1.getAttribute("value");

 //WebElement date_of_birth = LocatorUtils.getElement(locator_file,"date_of_birth");
  //dob = date_of_birth.getAttribute("value");
    List<WebElement> hobbies = LocatorUtils.getElements(locator_file, "hobbies");
    for (WebElement hobby : hobbies) {
        if (hobby.getText().equalsIgnoreCase("Sports")) {
            hobby.click();
            break;
        }
    }
    hobbies1= "Sports";
    WebElement state = LocatorUtils.getElement(locator_file, "Statedropdown");
    state.sendKeys("NCR");
    state.sendKeys(Keys.ENTER);
    WebElement city = LocatorUtils.getElement(locator_file, "cityDropdown");
    city.sendKeys("Delhi");
    city.sendKeys(Keys.ENTER);
}

    public void  submit_form()  {

        try {

            WebElement submitButton = LocatorUtils.getElement(locator_file,"submitButton");

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitButton);
            Thread.sleep(500);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConfirmationValue(String label) throws InterruptedException {

        Thread.sleep(Long.parseLong("100"));
        return driver.findElement(By.xpath("//td[text()='" + label + "']/following-sibling::td")).getText();
    }

    public void verifySubmittedDetails() throws InterruptedException {
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("Student Name", firstName + " " + lastName);
        expectedData.put("Student Email", email);
        expectedData.put("Gender", genderF);
        expectedData.put("Mobile", contact);
      //  expectedData.put("Date of Birth", dob);
        expectedData.put("Subjects", subjects1);
        expectedData.put("Hobbies", hobbies1);
        expectedData.put("Address", address);

        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String actual = getConfirmationValue(entry.getKey());
            Assert.assertEquals(actual, entry.getValue(), "❌ Mismatch for " + entry.getKey());
            System.out.println("Correct values "+actual+ ":"+entry.getKey());
        }
    }
    }
