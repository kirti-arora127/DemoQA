package com.niwi.stepdefinition;

import com.niwi.pageobjects.DietsPO;
import com.niwi.testbase.TestBase;
import com.niwi.utils.WaitUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DietsPage_SD extends TestBase {
       DietsPO dietsPO;
public DietsPage_SD(){
    dietsPO=new DietsPO(driver);
}
    @When("user clicks on diet tab")
    public void user_clicks_on_diet_tab()
    {
        WaitUtils.implicitWait();
        dietsPO.click_diets();
    }
    @And("user clicks on add diet button")
    public void userClicksOnAddDietButton() {
        dietsPO.click_add_diet();
        WaitUtils.implicitWait();
    }


    @And("user selects status of client")
    public void userSelectsStatusOfClient() {
        dietsPO.show_status();
    }

    @And("user selects client")
    public void userSelectsClient() {
        dietsPO.select_client();
    }


    @And("user enters current weight of client")
    public void userEntersCurrentWeightOfClient() {
        dietsPO.client_weight();
    }


    @And("user selects checkboxes")
    public void userSelectsCheckboxes() {
        dietsPO.select_options();
    }
    @And("user clicks on create  button")
    public void userClicksOnCreateButton() {
        dietsPO.create_diet();
    }

    @And("validate diet for client should be created")
    public void validateDietForClientShouldBeCreated() {
       dietsPO.validate_create_diet();
    }

    @And("user select  date for creating diet")
    public void userSelectDateForCreatingDiet() {
       dietsPO.select_dates_btn();
    }

    @And("user adds meal for client")
    public void userAddsMealForClient() {
        WaitUtils.implicitWait();
        dietsPO.add_meal();
    }


    @And("user adds remarks")
    public void userAddsRemarks() {
        dietsPO.add_remarks();
    }


    @And("user clicks on approve button")
    public void userClicksOnApproveButton() {
        WaitUtils.implicitWait();
        dietsPO.click_approve_diet();
    }

    @Then("diet for client is created")
    public void dietForClientIsCreated() {

        dietsPO.diet_created();
    }


    @And("user adds meal using different components")
    public void userAddsMealUsingDifferentComponents() {
            dietsPO.add_meal_UI();
    }

    @And("user assign master diets and prescribed diets")
    public void userAssignMasterDietsAndPrescribedDiets() {
        dietsPO.master_prescribed_diet();
    }

    @When("user clicks on action tab")
    public void userClicksOnActionTab() {

    }

    @And("user checks client diet details")
    public void userChecksClientDietDetails() {
    dietsPO.checkClientDietDetails();
    }


    @And("user checks different options in view diet tab")
    public void userChecksDifferentOptionsInViewDietTab() {
        dietsPO.optionsViewDiet();
    }

    @Then("all options work properly")
    public void allOptionsWorkProperly() {
    }



}
