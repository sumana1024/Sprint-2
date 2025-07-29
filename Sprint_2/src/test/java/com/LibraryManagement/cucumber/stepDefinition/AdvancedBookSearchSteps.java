package com.LibraryManagement.cucumber.stepDefinition;

import com.LibraryManagement.cucumber.pages.AdvancedBookSearchPage;

import io.cucumber.java.en.*;
import static org.testng.Assert.assertTrue;
import java.util.Map;

public class AdvancedBookSearchSteps {

	AdvancedBookSearchPage searchPage = new AdvancedBookSearchPage();

    @Given("I navigate to the {string} section of the application")
    public void i_navigate_to_the_section_of_the_application(String section) {
    	searchPage.openSite();
        searchPage.navigateToSearch();
        System.out.println("I navigated to the Search section of the application");
    }

    @When("I enter {string} in the Author Name field")
    public void i_enter_author_name(String author) {
        searchPage.enterAuthor(author);
        System.out.println("I entered Author Name: "+author);
    }

    @When("I enter {string} in the Subject field")
    public void i_enter_subject(String subject) {
        searchPage.enterSubject(subject);
        System.out.println("I entered Subject: "+subject);
    }

    @When("I select {string} from the Edition dropdown list")
    public void i_select_edition(String edition) {
        searchPage.selectEdition(edition);
        System.out.println("I selected Edition: "+edition);
    }

    @When("I select {string} from the Book Format dropdown list")
    public void i_select_book_format(String format) {
        searchPage.selectBookFormat(format);
        System.out.println("I selected Book Format: "+format);
    }

    @When("I select {string} from the Age Group radio buttons")
    public void i_select_age_group(String group) {
        searchPage.selectAgeGroup(group);
        System.out.println("I selected Age Group: "+group);
    }

    @When("I click the Submit button")
    public void i_click_submit_button() {
        searchPage.clickSubmit();
        System.out.println("I clicked the Submit button");
    }

    @Then("I should see a table containing details of the book searched")
    public void i_should_see_book_details() {
        assertTrue(searchPage.isResultsTableDisplayed());
        System.out.println("=====Test Pass: I saw a table containing details of the book searched=====");
    }

    @When("I leave all fields blank")
    public void i_leave_all_fields_blank() {
        System.out.println("I left all fields blank");
    }

    @Then("I should see appropriate error messages for all mandatory fields")
    public void i_should_see_error_messages() {
        assertTrue(searchPage.isErrorMessageDisplayed());
        System.out.println("=====Test Pass: Error message displayed for all mandatory fields=====");
    }

    @Then("I should see a table containing all the details of the book searched")
    public void i_should_see_all_book_details() {
        Map<String, String> actualDetails=searchPage.getDisplayedBookDetails();

        assert actualDetails.get("Author").equalsIgnoreCase("Erica Jong");
        assert actualDetails.get("Edition").equalsIgnoreCase("2");
        assert actualDetails.get("Subject").equalsIgnoreCase("non-fiction");
        assert actualDetails.get("Format").equalsIgnoreCase("Magazines");
        assert actualDetails.get("Age").equalsIgnoreCase("adult");

        System.out.println("======Test Pass: I saw a table containing all the details of the book searched======");
    }

    @Then("I should see an appropriate error message indicating no results found")
    public void i_should_see_no_results_error() {
        if (!searchPage.isErrorMessageDisplayedNoBooksFound()) 
        {
            throw new AssertionError("=====Test failed: No new elements appeared after form submission.=====");
        }
    }

    @Then("I should see a table containing details of all the available books by the selected author")
    public void i_should_see_books_by_author() {
        assertTrue(searchPage.isResultsTableDisplayed());
    }

    @Then("I should see a table containing details of all the available books of the selected subject")
    public void i_should_see_books_by_subject() {
        assertTrue(searchPage.isResultsTableDisplayed());
    }

    @Then("I should see a table containing details of all the available books of the selected book format")
    public void i_should_see_books_by_format() {
        assertTrue(searchPage.isResultsTableDisplayed());
    }

    @Then("I should see a table containing details of all the available books of the selected age group")
    public void i_should_see_books_by_age_group() {
        assertTrue(searchPage.isResultsTableDisplayed());
    }
}
