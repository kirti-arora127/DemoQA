package com.niwi.stepdefinition;

import com.niwi.pageobjects.SettingsFormsPO;
import com.niwi.testbase.TestBase;
import com.niwi.utils.WaitUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class SettingsForms_SD extends TestBase {
    SettingsFormsPO settingsFormsPO;
    public  SettingsForms_SD(){
        settingsFormsPO = new SettingsFormsPO(driver);
    }
    @When("User click on settings, Accounts, Form tab")
    public void userClickOnSettingsAccountsFormTab() {
        WaitUtils.implicitWait();
        settingsFormsPO.settingsTab();
    }

    @When("User goes to health Progress form")
    public void userGoesToHealthProgressForm() {
        settingsFormsPO.health_progress();
    }

    @And("save health progress form")
    public void saveHealthProgressForm() throws InterruptedException {
        settingsFormsPO.savehealthprogress();
    }

    @When("user clicks on client tab\\(Angular)")
    public void userClicksOnClientTabAngular() {

        settingsFormsPO.userClicksOnClientTabAngular();
    }
}
