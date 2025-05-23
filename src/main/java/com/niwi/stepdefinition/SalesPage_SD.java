package com.niwi.stepdefinition;

import com.niwi.pageobjects.SalesPO;
import com.niwi.testbase.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SalesPage_SD extends TestBase {
        SalesPO salesPO;
        public SalesPage_SD(){
            salesPO=new SalesPO(driver);
        }

        @When("user clicks on sales tab")
        public void user_clicks_on_sales_tab(){
            salesPO.sales_tab();
        }



    @And("user creates new  client")
    public void userCreatesNewClient() {
        salesPO.create_new_client();
    }

    @And("user add plan for client")
    public void userAddPlanForClient() {
            salesPO.create_plan();
            salesPO.add_discount();
    }
    @Then("invoice is created")
    public void invoiceIsCreated() {
            salesPO.invoice_created();
            salesPO.update_cps();
            salesPO.running_options();
    }



    @When("user clicks on Receipt master")
    public void userClicksOnReceiptMaster() {
        salesPO.click_receipt_master();
    }

    @And("user clicks on add payment button to add payment")
    public void userClicksOnAddPaymentButtonToAddPayment() {
        salesPO.add_payment();
    }

    @And("user links payment to invoice")
    public void userLinksPaymentToInvoice() {
        salesPO.link_payment();
    }


    @Then("balance gets updated and performs actions on invoice")
    public void balanceGetsUpdatedAndPerformsActionsOnInvoice() {
            salesPO.check_balance();
    }


    @When("user clicks on client programmes tab")
    public void userClicksOnClientProgrammesTab() {
        salesPO.click_client_programme();
    }

    @And("user selects particular status")
    public void userSelectsParticularStatus() {
        salesPO.handle_status();
    }

    @Then("list based on status should be displayed")
    public void listBasedOnStatusShouldBeDisplayed(){
    System.out.println("List displayed ");
    }


    @When("user selects financial year")
    public void userSelectsFinancialYear() {
        salesPO.select_financial_year();
    }

    @And("user clicks on submit button")
    public void userClicksOnSubmitButton() {
        salesPO.click_submit();
    }

    @Then("sales of given financial year is displayed")
    public void salesOfGivenFinancialYearIsDisplayed() {
        salesPO.report_displayed();
    }

    @And("click on advance search button")
    public void clickOnAdvanceSearchButton() {
        salesPO.click_advance_search();
    }

    @And("selects advance search filters")
    public void selectsAdvanceSearchFilters() {
            salesPO.advance_search_filters();
    }




}
