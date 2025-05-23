package com.niwi.stepdefinition;

import com.niwi.pageobjects.MarketingPO;
import com.niwi.testbase.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MarketingPage_SD extends TestBase {
    MarketingPO marketingPO;
    public MarketingPage_SD(){

        marketingPO=new MarketingPO(driver);
    }

    @When("user clicks on marketing tab")
    public void userClicksOnMarketingTab(){
        marketingPO.user_clicks_on_marketing_tab();
    }

    @And("user creates a lead")
    public void userCreatesALead() {
        marketingPO.user_creates_lead();
    }

    @Then("lead gets created")
    public void leadGetsCreated() {
        marketingPO.lead_gets_created();
    }

    @When("user opens webpage")
    public void userOpensWebpage() {
        marketingPO.user_opens_webpage();
    }

    @And("user creates a lead from webpage")
    public void userCreatesALeadFromWebpage() {
        marketingPO.create_lead_From_webpage();
    }

    @And("user searches for specific leads")
    public void userSearchesForSpecificLeads() {
        marketingPO.search_leads();
    }

    @Then("leads gets displayed")
    public void leadsGetsDisplayed() {
        System.out.println("Leads displayed");
       marketingPO.filtered_results();

    }
    @When("user clicks on advanced search button")
    public void userClicksOnAdvancedSearchButton() {
        marketingPO.click_advance_search_btn();
    }

    @And("user fills filters and clicks on submit button")
    public void userFillsFiltersAndClicksOnSubmitButton() {
        marketingPO.submit_filters();
    }

    @Then("user shows filtered results")
    public void userShowsFilteredResults() {
        System.out.println("Leads displayed");
        marketingPO.filtered_results();
    }


    @And("user edits a lead")
    public void userEditsALead() {
        marketingPO.edit_lead();
    }

    @Then("lead gets edited")
    public void leadGetsEdited() {
        marketingPO.lead_gets_edited();
    }

    @And("user adds comments")
    public void userAddsComments() {
        marketingPO.add_comments();
    }

    @Then("comments get added")
    public void commentsGetAdded() {
        marketingPO.display_comments();
    }
}
