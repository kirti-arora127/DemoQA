Feature: Marketing Leads all scenarios
  @smoke1
  Scenario: Creating Lead using add lead button
  Given User Logged into application
  When user clicks on marketing tab
  And user creates a lead
  Then lead gets created



  Scenario: Editing a lead
    When user edits a lead
    Then lead gets edited


  #Scenario: Searching leads
  #When user searches for specific leads
  #Then leads gets displayed

    @smoke1
  Scenario: Automating Advanced Search options
    When user clicks on advanced search button
    And user fills filters and clicks on submit button
    Then user shows filtered results

    Scenario:Automating comments section
     When user adds comments
    Then comments get added


  #Scenario: Creating Lead from webpage
  #  Given User Logged into application
   # When user opens webpage
    #And user creates a lead from webpage
    ###Then lead gets created

