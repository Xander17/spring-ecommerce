Feature: Add category and product

  Scenario Outline: Successful Login, add category, product for this category, find and delete both
    Given Open web browser
    When Go to login page
    And Fill form with <username> and <password>
    And Click on login button
    Then Go to Category page
    And Fill new category name <category>
    And Click add category button
    And Check <category> exists
    Then Go to product page
    And Click to Add Product button
    Then Fill form with <title>, <description>, <category>, <price>
    And Click Add button
    Then Fill filter with <title> and set filter by button click
    And Find on page product: <title>, <description>, <category>, <price>
    Then Find product: <title>, <description>, <category>, <price> and click delete button
    Then Fill filter with <title> and set filter by button click
    And Check there are no created product: <title>, <description>, <category>, <price>
    Then Go to Category page
    And Find and delete created <category>
    And Check there are no created category: <category>
    Then Quit web browser

    Examples:
      | username | password | title       | description     | category     | price |
      | sadmin   |          | testproduct | testdescription | testcategory | 42    |



