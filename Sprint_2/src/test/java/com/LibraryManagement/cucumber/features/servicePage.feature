@ServiceQuery
Feature: Contact and Query resolving services

  As a user
  I want to use different service options (Email, Call, Chat)
  So that I can contact and resolve my queries

  @TCS001
  Scenario: Validate only one option selection in service page
    Given I open the site and navigate to the Services page
    When I select the following service options:
      | Email |
      | Call  |
      | Chat  |
    Then Only one option should remain selected

  @TCS002 @EmailValid
  Scenario: Validate Email option with different email inputs
    Given I open the site and navigate to the Services page
    When I select the "Email" option
    And I fill the email form with the following data:
      | Email ID           | Query                         |
      | valid@example.com  | Need help with my account     |
    And I click the submit button
    Then I should see a successful email validation message

  @TCS003 @EmailInvalid
  Scenario: Validate Email option with invalid email id
    Given I open the site and navigate to the Services page
    When I select the "Email" option
    And I fill the email form with the following data:
      | Email ID     | Query                         |
      | invalid-email | Need help with my account    |
    And I click the submit button
    Then I should see an error message for invalid email

  @TCS004 @EmailBlank
  Scenario: Validate Email option with blank email id
    Given I open the site and navigate to the Services page
    When I select the "Email" option
    And I fill the email form with the following data:
      | Email ID | Query                         |
      |          | Need help with my account     |
    And I click the submit button
    Then I should see an error message for blank email

  @TCS005 @ChatValidPhone
  Scenario: Validate Chat section with valid phone number
    Given I open the site and navigate to the Services page
    When I select the "Chat" option
    And I fill the chat form with the following data:
      | Name         | Phone Number | Query                         |
      | Sumana Manna | 9876543210   | Need help with my account     |
    And I click the submit button
    Then I should see a successful phone validation message

  @TCS006 @ChatInvalidPhone
  Scenario: Validate Chat section with invalid phone number
    Given I open the site and navigate to the Services page
    When I select the "Chat" option
    And I fill the chat form with the following data:
      | Name         | Phone Number | Query                         |
      | Sumana Manna | abc123       | Need help with my account     |
    And I click the submit button
    Then I should see an error message for invalid phone

  @TCS007 @ChatBlankPhone
  Scenario: Validate Chat section with blank phone number
    Given I open the site and navigate to the Services page
    When I select the "Chat" option
    And I fill the chat form with the following data:
      | Name         | Phone Number | Query                         |
      | Sumana Manna |              | Need help with my account     |
    And I click the submit button
    Then I should see an error message for blank phone number

  @TCS008 @ChatValidName
  Scenario: Validate Chat section with valid name
    Given I open the site and navigate to the Services page
    When I select the "Chat" option
    And I fill the chat form with the following data:
      | Name         | Phone Number | Query                         |
      | Sumana Manna | 9876543210   | Need help with my account     |
    And I click the submit button
    Then I should see a successful name validation message

  @TCS009 @ChatInvalidName
  Scenario: Validate Chat section with invalid name
    Given I open the site and navigate to the Services page
    When I select the "Chat" option
    And I fill the chat form with the following data:
      | Name   | Phone Number | Query                         |
      | 1234@@ | 9876543210   | Need help with my account     |
    And I click the submit button
    Then I should see an error message for invalid name

  @TCS010 @ChatBlankName
  Scenario: Validate Chat section with blank name
    Given I open the site and navigate to the Services page
    When I select the "Chat" option
    And I fill the chat form with the following data:
      | Name | Phone Number | Query                         |
      |      | 9876543210   | Need help with my account     |
    And I click the submit button
    Then I should see an error message for blank name
