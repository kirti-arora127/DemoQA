package com.niwi.stepdefinition;

import com.niwi.pageobjects.CalendarPO;
import com.niwi.testbase.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalendarPage_SD extends TestBase {
        CalendarPO calendarPO;
        public CalendarPage_SD(){
           calendarPO=new CalendarPO(driver);
        }
    @When("user clicks on calendar tab")
    public void userClicksOnCalendarTab() {
        calendarPO.click_calendar_tab();
    }

    @And("user books an appointment")
    public void userBooksAnAppointment() {
            calendarPO.book_appointment();
        
    }

    @Then("appointment gets booked")
    public void appointmentGetsBooked() {
            calendarPO.appointment_booked();
    }

    @When("user checks appointments")
    public void userChecksAppointments() {
        calendarPO.check_appointments();
    }


    @Then("appointments should be displayed")
    public void appointmentsShouldBeDisplayed() {
            calendarPO.appointments_displayed();
    }

    @When("user selects one appointment")
    public void userSelectsOneAppointment() {
            calendarPO.select_appointment();
    }

    @And("perform various actions")
    public void performVariousActions() {
        calendarPO.calendar_actions();
    }

    @Then("action is performed")
    public void actionIsPerformed() {
            calendarPO.verify_action();
    }
}
