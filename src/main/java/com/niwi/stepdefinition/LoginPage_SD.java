package com.niwi.stepdefinition;

import com.niwi.pageobjects.LoginPO;
import com.niwi.testbase.TestBase;
import com.niwi.utils.LocatorUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage_SD extends TestBase {
    static  LocatorUtils locatorUtils;
    static LoginPO loginPO;


    public static  void user_open_url(){
        if(driver==null)
        {
            openBrowser();
        }
        loginPO=new LoginPO(driver);
        locatorUtils=new LocatorUtils(driver);
        driver.get("https://dev.niwi.ai/");
    }



    public void userEntersValidUsername() {
        loginPO.enter_username();
    }



    public void userEntersValidPassword() {
        loginPO.enter_password();
    }


    public void userClicksOnLoginButton() {
        loginPO.clickOnLogin();
    }



    public void userShouldLandOnHomepage() throws InterruptedException{
        //loginPO.validateUserLOggedInSuccessfully();
    }

    @Given("user logs out")
    public void userLogsOut() {
    }

    @Given("User Logged into application")
    public void userLoggedIntoApplication() {
        try {
            user_open_url();
            userEntersValidUsername();
            userEntersValidPassword();
            userClicksOnLoginButton();
            userShouldLandOnHomepage();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
