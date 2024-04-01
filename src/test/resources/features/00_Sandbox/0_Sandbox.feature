@sandboxTests
Feature: Testing separate scenarios under development


  Scenario: eBay Test
    Given the user is on eBay homepage "eBay_Homepage"
    And the user accepts all cookies
    When the user navigates to the 'Toys' category
    And the user searches for 'Monopoly'
    And the user selects one of the Monopoly Products
    And the user navigates to the 'Shipping, returns, and payments' tab on PDP
    And the user adds the product to Cart

    
