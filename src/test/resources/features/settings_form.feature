Feature: Settings Dynamic Form

Scenario: Automating dynamic Health Progress Form(Edit) in settings
Given User Logged into application
  When User click on settings, Accounts, Form tab
  When User goes to health Progress form
  And save health progress form

Scenario: Go to Client details
  When user clicks on client tab(Angular)
  And user selects a client
