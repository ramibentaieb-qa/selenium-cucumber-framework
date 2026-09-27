Feature: User Login Functionality

  Background:
    Given the user is on the SauceDemo login page

  Scenario Outline: Verify user login with various credentials
    When the user enters username "<username>" and password "<password>"
    And clicks on the login button
    Then the user should see "<result>"

    Examples:
      | username                | password     | result                 |
      | standard_user           | secret_sauce | Products               |
      | locked_out_user         | wrong_password | Epic sadface: Sorry... |