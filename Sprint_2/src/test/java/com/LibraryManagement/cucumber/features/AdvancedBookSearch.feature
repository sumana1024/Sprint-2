@AdvancedBookSearch
Feature: Advanced Search of Book by Multiple Filters
  
  @TCSH001 
  Scenario: TCSH001 - Validate Available Book Displayed On Search
    Given I navigate to the "Search" section of the application
    When I enter "Antony" in the Author Name field
    And I enter "fiction" in the Subject field
    And I select "Edition 4" from the Edition dropdown list
    And I select "NewsPaper" from the Book Format dropdown list
    And I select "teen" from the Age Group radio buttons
    And I click the Submit button
    Then I should see a table containing details of the book searched
    
  @TCSH002
  Scenario: TCSH002 - Validate All Mandatory Fields
    Given I navigate to the "Search" section of the application
    When I leave all fields blank
    And I click the Submit button
    Then I should see appropriate error messages for all mandatory fields
    
  @TCSH003
  Scenario: TCSH003 - Validate All Details of the Searched Book Displayed
    Given I navigate to the "Search" section of the application
    When I enter "Erica Jong" in the Author Name field
    And I enter "non-fiction" in the Subject field
    And I select "Edition 2" from the Edition dropdown list
    And I select "Magazines" from the Book Format dropdown list
    And I select "adult" from the Age Group radio buttons
    And I click the Submit button
    Then I should see a table containing all the details of the book searched
    
  @TCSH004
  Scenario: TCSH004 - Validate Error Message For Searching Non Existent Book
    Given I navigate to the "Search" section of the application
    When I enter "Emily" in the Author Name field
    And I enter "horror" in the Subject field
    And I select "Edition 2" from the Edition dropdown list
    And I select "Magazines" from the Book Format dropdown list
    And I select "kids" from the Age Group radio buttons
    And I click the Submit button
    Then I should see an appropriate error message indicating no results found
    
 @TCSH005
  Scenario: TCSH005 - Validate Books By Selected Author Displayed In Search
    Given I navigate to the "Search" section of the application
    When I enter "Philip Roth" in the Author Name field
    And I click the Submit button
    Then I should see a table containing details of all the available books by the selected author
  
  @TCSH006
  Scenario: TCSH006 - Validate Books By Selected Subject Displayed In Search
    Given I navigate to the "Search" section of the application
    When I enter "fiction" in the Subject field
    And I click the Submit button
    Then I should see a table containing details of all the available books of the selected subject

  @TCSH007
  Scenario: TCSH007 - Validate Books By Selected Book Format Displayed In Search
    Given I navigate to the "Search" section of the application
    When I select "E_Books" from the Book Format dropdown list
    And I click the Submit button
    Then I should see a table containing details of all the available books of the selected book format

  @TCSH008
  Scenario: TCSH008 - Validate Books By Selected Age Group Displayed In Search
    Given I navigate to the "Search" section of the application
    When I select "teen" from the Age Group radio buttons
    And I click the Submit button
    Then I should see a table containing details of all the available books of the selected age group
