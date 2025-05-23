Feature: Client Page all Scenarios

   Scenario: Automating dynamic Onboarding Form for new client
     Given User Logged into application
     When user clicks on client tab
     And user adds a client
     And user generates onboarding form
     And user fills generated onboarding form
     Then onboarding form gets filled

  @smoke1
  Scenario: Automating dynamic Onboarding Form(Edit) for existing client
    Given User Logged into application
    When user clicks on client tab
    And user selects a client
    And user check for onboarding form
    Then onboarding form gets filled


