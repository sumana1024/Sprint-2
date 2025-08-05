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
    And I select Gold Membership
    Then Gold Membership should be selected
    And Displayed amount should be Rs.5000

  @TCM005
  Scenario: Select Platinum Membership and verify amount
    Given I navigate to the Library Membership page
    When I complete the human verification
    And I select Platinum Membership
    Then Platinum Membership should be selected
    And Displayed amount should be Rs.25000

  @TCM_BULK_SUBMIT
  Scenario: Submit membership form with multiple entries
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I submit the following membership entries:
      | MembershipType | CardNumber | ExpectedMessage                      |
      | Gold           | LCN_002    | Membership Added                     |
      | Platinum       | LCN_003    | Membership Added                     |
      | Gold           |            | Please Enter Your Card number        |
      | Platinum       |            | Please Enter Your Card number        |
      | Gold           | LCN_004    | Membership Added (Reset Expected)    |
  
  @TCM_DUPLICATE_CARD
  Scenario: Submit duplicate card number and expect rejection
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I submit the following membership entries:
      | MembershipType | CardNumber | ExpectedMessage |
      | Gold           | LCN_005    | Membership Added |
      | Platinum       | LCN_005    | already          |
      
  @TCM_BULK_EXCEL
  Scenario: Submit membership form using Excel data
    Given I navigate to the Library Membership page
    And I complete the human verification
    When I submit the membership entries from Excel
  
  @TCME001
  Scenario: View Members with Memberships
    Given I navigate to the Library Membership page
    When I complete the human verification
    And I select any membership type
    And I enter card number "LCN_007"
    And I click Submit
    And I navigate to the Members page
    Then The members table should display the newly added member
