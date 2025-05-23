package com.niwi.stepdefinition;

import com.niwi.pageobjects.ClientsPO;
import com.niwi.testbase.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class ClientsPage_SD extends TestBase {
    ClientsPO clientsPO;

    public ClientsPage_SD() {
        clientsPO = new ClientsPO(driver);
    }

    @When("user clicks on client tab")
    public void userClicksOnClientTab() {
        clientsPO.click_client_tab();
    }

    @And("user adds a client")
    public void userAddsAClient() {
        clientsPO.add_new_client();
    }

    @And("user generates onboarding form")
    public void userGeneratesOnboardingForm() {
        clientsPO.generate_dynamic_onboarding();

    }
    //for all sections
    @And("user fills onboarding form")
    public void userFillsOnboardingForm() {
        clientsPO.fill_onboarding();
    }
    //selected sections
    @And("user fills generated onboarding form")
    public void userFillsGeneratedOnboardingForm() {
        clientsPO.fill_dynamic_onboarding_form();
    }


    @Then("onboarding form gets filled")
    public void onboardingFormGetsFilled() {
        clientsPO.validate_onboarding();
    }

    @And("user selects a client")
    public void userSelectsAClient() {
        clientsPO.select_client();
    }

    @And("user check for onboarding form")
    public void userCheckForOnboardingForm() {
       clientsPO.check_for_onboarding_form();

    }


//    @And("user selects a client")
//    public void userSelectsAClient() {
//        clientsPO.select_client();
//    }
//
//    @And("user edits generated onboarding form")
//    public void userEditsGeneratedOnboardingForm() {
//        clientsPO.edit_dynamic_onboarding_form();
//    }

}