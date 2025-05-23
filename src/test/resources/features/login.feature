Feature: Login with valid data

  Scenario: Login should be Successful with valid Username and Password
    Given  user opens url
    When user enters valid username
    And user enters valid password
    And user clicks on login button
    Then user should land on homepage

