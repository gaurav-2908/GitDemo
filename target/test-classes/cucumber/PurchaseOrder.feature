
@tag
Feature: Purchase the order from eCommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on eCommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given I login with username <username> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username 				 					| password  | productName	|
      | gaurav.jangde1@gmail.com	|Password@1 |ZARA COAT 3  |
      | testingqa123@test.com		  |Password@1 |ZARA COAT 3  |