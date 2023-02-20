Feature: Login with Valid Credentials

  @sanity @regression
  Scenario: Successful Login with Valid Credentials
    Given User Launch browser
    And Opens URL "http://localhost/opencart/upload/"
    When User Navigate to MyAccount menu
    And click on Login
    And User enters Email as "test@gmail.com" and Password as "test@123"
    And Click on Login button
    Then User Navigates to MyAccount Page
