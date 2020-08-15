Feature: Login

  Scenario Outline: Successful Login to the page and logout after
    Given Open web browser
    When Go to login page
    And Fill form with <username> and <password>
    And Click on login button
    Then Check name that should be <name>
    When Open dropdown menu
    And Click logout button
    Then User logged out
    Then Quit web browser

    Examples:
      | username | password | name   |
      | sadmin   |          | sadmin |



