# Library Management System
## project summary
This project is a complete test automation solution tailored for validating key features of a Library Membership Management System. It is developed using Selenium WebDriver for browser-based interaction and follows the Behavior-Driven Development (BDD) model using Cucumber. Test scenarios are written in Gherkin syntax and executed with TestNG. The framework emphasizes modular design through the Page Object Model (POM) and supports scalable test automation.
## Technology Used
Java 21

Selenium WebDriver

Cucumber (BDD Framework)

TestNG

Maven (Project and Dependency Management)
## Functional Modules Under Test
### - Advanced Book Search
- Book search functionality with multiple filters
- Search by author name, subject, edition, format, and age group
- Input validation for required fields
- "All Books" section for browsing the complete collection
### Book Search
- Implements a search bar for books with live filtering by name or author.
- Displays appropriate feedback for both valid and invalid search inputs.
- Supports reset functionality to clear filters and restore the full book list.
- Includes UX enhancements like auto-refresh and field labeling for clarity.
### Services and Query
- Provides multiple contact options: Email, Call, and Chat, selectable via radio buttons.
- Validates required input fields like email ID, phone number, and name before submission.
- Displays context-specific error messages for blank or invalid inputs.
- Confirms successful query submission with dynamic feedback for each contact method.
### Library Membership Functionality
- Includes a drag-and-drop based human verification system for form access.
- Supports membership type selection with dynamic fee display (e.g., Gold: ₹5000, Platinum: ₹25000).
- Validates card number field and prevents duplicate submissions.
- Displays submitted members in a dynamic members table for verification.



