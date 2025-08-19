package com.demoqa.StepDefinitions;

import com.demoqa.PageObject.RegistrationPO;
import com.demoqa.TestBase.TestBase;
import com.demoqa.utils.LocatorUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSD extends TestBase {

    static  LocatorUtils locatorUtils;
    static RegistrationPO registrationPO;
  @Before
    public static  void user_open_url(){
        if(driver==null)
        {
            openBrowser();
        }
        registrationPO=new RegistrationPO(TestBase.driver);
        locatorUtils=new LocatorUtils(driver);
        driver.get("https://demoqa.com/");
    }


    @Given("I open the registration form page")
    public void iOpenTheRegistrationFormPage() {


     //   registrationPO=new RegistrationPO(driver);
       // locatorUtils=new LocatorUtils(driver);
        registrationPO.OpenTheRegistrationFormPage();
    }

    @When("I fill in all required details correctly")
    public void iFillInAllRequiredDetailsCorrectly() {

      try {
          registrationPO.fill_form();
      } catch (RuntimeException e) {
          throw new RuntimeException(e);
      }

    }

    @And("I submit the form")
    public void iSubmitTheForm() {
    }

    @Then("I should see the confirmation with submitted details")
    public void iShouldSeeTheConfirmationWithSubmittedDetails() {

    }
}
