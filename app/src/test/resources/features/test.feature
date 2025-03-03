@Cart @UI
Feature: Shopping Cart Management

  Scenario: Add and remove item from the shopping cart
    Given I navigate to the OpenCart page
    When I enter "iPhone" in the search bar and submit the search
    And I select the first product from the search results
    And I add the product to the shopping cart
    And I click the shopping cart button
    And I click on the view cart button
    Then I should see "iPhone" in the shopping cart
    When I remove "iPhone" from the shopping cart
    Then I should not see "iPhone" in the shopping cart
    And the cart total should be zero
