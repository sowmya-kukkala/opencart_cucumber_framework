Feature: Login Data Driven

  Scenario Outline: Login Data Driven
    Given User Launch browser
    And Opens URL "http://localhost/opencart/upload/"
    When User Navigate to MyAccount menu
    And click on Login
    And User enters Email as "<email>" and Password as "<password>"
    And Click on Login button
    Then User Navigates to MyAccount Page

    Examples: 
      | email          | password |
      | test@gmail.com | test@123 |
      | test@gmail.com | test123  |
