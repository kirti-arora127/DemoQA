Feature: Diet Page All Scenarios


  Scenario: Assigning Master diets and Prescribed diets
    Given User Logged into application
    When  user clicks on diet tab
    And user clicks on add diet button
    And user selects status of client
    And user selects client
    And user enters current weight of client
    And user selects checkboxes
    And user clicks on create  button
    And user assign master diets and prescribed diets
    And user adds remarks
    And user clicks on approve button
    Then diet for client is created


  Scenario: Create New Diet
    When  user clicks on add diet button
    And user selects status of client
    And user selects client
    And user enters current weight of client
    And user selects checkboxes
    And user clicks on create  button
    And validate diet for client should be created
    And user select  date for creating diet
    And user adds meal for client
    And user adds remarks
    And user clicks on approve button
    Then diet for client is created


  Scenario: View Diet all Options
    When  user clicks on diet tab
    And user checks client diet details
    And user checks different options in view diet tab
    Then all options work properly


  Scenario: Testing UI components
    When user clicks on add diet button
    And user selects status of client
    And user selects client
    And user enters current weight of client
    And user selects checkboxes
    And user clicks on create  button
    And user adds meal using different components
    And user adds remarks
    And user clicks on approve button
    Then diet for client is created








