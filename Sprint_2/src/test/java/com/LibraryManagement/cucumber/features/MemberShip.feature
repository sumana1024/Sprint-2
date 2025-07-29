@Membership
Feature: Apply for Library Membership
  As a user
  I want to complete human verification and select a membership type
  So that I can register for library services

  @TCM001
  Scenario: Verify successful Human Verification via drag and drop
    Given I navigate to the Library Membership page
    When I drag and drop the book into the correct box
    Then Human Verification section should be hidden
    And Membership form should be visible

  @TCM002
  Scenario: Verify failed Human Verification with incorrect drop location
    Given I navigate to the Library Membership page
    When I drag the book but drop it outside the box
    Then Human Verification section should remain visible
    And Membership form should remain hidden

  @TCM003
  Scenario: Verify failed Human Verification with no drag-drop action
    Given I navigate to the Library Membership page
    When I wait without dragging the book
    Then Human Verification section should remain visible
    And Membership form should remain hidden

  @TCM004
  Scenario: Select Gold Membership and verify amount
    Given I navigate to the Library Membership page
    When I complete the human verification
    When I select Gold Membership
    Then Gold Membership should be selected
    And Displayed amount should be Rs.5000

  @TCM005
  Scenario: Select Platinum Membership and verify amount
    Given I navigate to the Library Membership page
    When I complete the human verification
    When I select Platinum Membership
    Then Platinum Membership should be selected
    And Displayed amount should be Rs.25000

  @TCM006
  Scenario: Submit form with Gold Membership and valid card
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I select Gold Membership
    And I enter card number "LCN_002"
    And I click Submit
    Then Form should submit successfully

  @TCM007
  Scenario: Submit form with Platinum Membership and valid card
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I select Platinum Membership
    And I enter card number "LCN_003"
    And I click Submit
    Then Form should submit successfully

  @TCM008
  Scenario: Submit Gold Membership with empty card number
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I select Gold Membership
    And I leave card number empty
    And I click Submit
    Then Error message should appear for required field

  @TCM009
  Scenario: Submit Platinum Membership with empty card number
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I select Platinum Membership
    And I leave card number empty
    And I click Submit
    Then Error message should appear for required field

  @TCM010
  Scenario: Verify card number field resets after successful submission
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I select Gold Membership
    And I enter card number "LCN_004"
    And I click Submit
    Then Form should submit successfully
    And Card number field should be reset

  @TCM011
  Scenario: Prevent reuse of card number for different memberships
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I select Gold Membership
    And I enter card number "LCN_005"
    And I click Submit
    And I wait for confirmation message
    And I complete the human verification again
    And I select Platinum Membership
    And I enter card number "LCN_005"
    And I click Submit
    Then Error message should appear for duplicate number
    
  @TCME001
  Scenario: View Members with Memberships
    Given I navigate to the Library Membership page
    When I complete the human verification
    And I select any membership type
    And I enter card number "LCN_007"
    And I click Submit
    And I navigate to the Members page
    Then The members table should display the newly added member