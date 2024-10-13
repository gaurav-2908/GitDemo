
@tag
Feature: Error Validation
  I want to use this template for my feature file
    

  @ErroValidation
  Scenario Outline: Verify required error message is displayed for incorrect credentials
    Given I landed on eCommerce page
    When I login with username <username> and password <password>
    Then "Incorrect email or password." message is displayed
    

    Examples: 
      | username 				 					| password  |
      | testingqa123@test.com		  |zassword	  |