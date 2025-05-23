Feature: Working of calendar

  Scenario: Working of calendar
  Given User Logged into application
  When user clicks on calendar tab
  And user books an appointment
  Then appointment gets booked

  Scenario: Checking appointments
    When user checks appointments
    Then appointments should be displayed

  Scenario: Automating actions in Calendar
    When user selects one appointment
    And perform various actions
    Then action is performed