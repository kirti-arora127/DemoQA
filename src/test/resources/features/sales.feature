
Feature: Sales Page All Scenarios


  Scenario:Create Invoice(Inclusive)
    Given User Logged into application
    When user clicks on sales tab
    And user creates new  client
    And user add plan for client
    Then invoice is created


  Scenario:Adding and Linking payment to invoice
    When user clicks on Receipt master
    And user clicks on add payment button to add payment
    And user links payment to invoice
    Then balance gets updated and performs actions on invoice


  Scenario:Checking client programmes status
    When user clicks on client programmes tab
    And user selects particular status
    Then list based on status should be displayed


  Scenario:Testing Sales Report
    When user selects financial year
    And user clicks on submit button
    Then sales of given financial year is displayed

  Scenario:Testing Advanced search
    When click on advance search button
    And selects advance search filters
    And user clicks on submit button
    Then sales of given financial year is displayed


