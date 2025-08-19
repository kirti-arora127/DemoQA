Feature:  Registration form
Scenario:Submit registration form
  Given I open the registration form page
    When I fill in all required details correctly
    And I submit the form
    Then I should see the confirmation with submitted details