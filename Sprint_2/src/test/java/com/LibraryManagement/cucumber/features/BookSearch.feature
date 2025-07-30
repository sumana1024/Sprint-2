@BookSearch
Feature: View the List of Available Books

  Scenario: Verify Available Books List Display in Books Section
    Given User navigates to the Library Management Application
    When User clicks on the "Books" section
    And User enters "Letting Go" into the search box
    And User clears the search box
    And User refreshes the page
    Then Complete list of all available books should reappear

  Scenario: Validate correct book is fetched when searching by book name
    Given User navigates to the Books page
    When User enters "Letting Go" into the search box
    Then The correct book matching the entered name should be displayed

  Scenario: Validate system behavior when searching for a nonexistent book
    Given User navigates to the Books page
    When User enters a nonexistent book name into the search box
    Then System should display a message "No books found"

  Scenario: Validate search bar labeling for clarity on searchable fields
    Given User navigates to the Books page
    Then The search bar should clearly indicate "Search by Book Name or Author"

  Scenario: Validate Reset button clears search fields and restores full book list
    Given User navigates to the Library Management Application
    When User clicks on the "Books" section
    And User enters "Letting Go" into the search box
    And User clicks the Reset button
    Then All input fields should be cleared and complete list of books displayed