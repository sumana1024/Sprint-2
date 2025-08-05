package com.LibraryManagement.cucumber.stepDefinition;

import com.LibraryManagement.cucumber.pages.AdvancedBookSearchPage;
import com.LibraryManagement.cucumber.utils.ExcelReader;

import io.cucumber.java.en.*;
import static org.testng.Assert.assertTrue;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

public class AdvancedBookSearchSteps {

	//Page Object instance for interacting with the Advanced Search page
	AdvancedBookSearchPage searchPage = new AdvancedBookSearchPage();

	//Navigates to the Search section of the Library application
    @Given("I navigate to the {string} section of the application")
    public void i_navigate_to_the_section_of_the_application(String section) {
    	searchPage.openSite();
        searchPage.navigateToSearch();
        System.out.println("I navigated to the Search section of the application");
    }
    
    //Fill the Advanced Book Search form using data provided in the DataTable.
    @When("I provide the following book search details:")
    public void i_provide_the_following_book_search_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap();

        if (data.containsKey("Author")) {
            searchPage.enterAuthor(data.get("Author"));
        }
        if (data.containsKey("Subject")) {
            searchPage.enterSubject(data.get("Subject"));
        }
        if (data.containsKey("Edition")) {
            searchPage.selectEdition(data.get("Edition"));
        }
        if (data.containsKey("Format")) {
            searchPage.selectBookFormat(data.get("Format"));
        }
        if (data.containsKey("AgeGroup")) {
            searchPage.selectAgeGroup(data.get("AgeGroup"));
        }
        System.out.println("I provide details of book to search");
    }
    
    //Clicks the Submit button to execute the search.
    @When("I click the Submit button")
    public void i_click_submit_button() {
        searchPage.clickSubmit();
        System.out.println("I clicked the Submit button");
    }

    //Validates that a result table is displayed after performing a search.
    @Then("I should see a table containing details of the book searched")
    public void i_should_see_book_details() {
        assertTrue(searchPage.isResultsTableDisplayed());
        System.out.println("=====Test Pass: I saw a table containing details of the book searched=====");
    }

    //Leaves all fields blank 
    @When("I leave all fields blank")
    public void i_leave_all_fields_blank() {
        System.out.println("I left all fields blank");
    }

    //Validates that error messages are displayed for all mandatory fields.
    @Then("I should see appropriate error messages for all mandatory fields")
    public void i_should_see_error_messages() {
        assertTrue(searchPage.isErrorMessageDisplayed());
        System.out.println("=====Test Pass: Error message displayed for all mandatory fields=====");
    }

    //Validates that all the details of the searched book match expected values.
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

    //Verifies that an appropriate error message or some indication is shown when no book is found.
    @Then("I should see an appropriate error message indicating no results found")
    public void i_should_see_no_results_error() {
        if (!searchPage.isErrorMessageDisplayedNoBooksFound()) 
        {
            throw new AssertionError("=====Test failed: No new elements appeared after form submission.=====");
        }
    }

    //Validates that search results include all available books by the selected author.
    @Then("I should see a table containing details of all the available books by the selected author")
    public void i_should_see_books_by_author() {
        assertTrue(searchPage.isResultsTableDisplayed());
    }

    //Validates that search results include all available books with the selected subject.
    @Then("I should see a table containing details of all the available books of the selected subject")
    public void i_should_see_books_by_subject() {
        assertTrue(searchPage.isResultsTableDisplayed());
    }

    //Validates that search results include all available books of the selected book format.
    @Then("I should see a table containing details of all the available books of the selected book format")
    public void i_should_see_books_by_format() {
        assertTrue(searchPage.isResultsTableDisplayed());
    }

    //Validates that search results include all available books for the selected age group.
    @Then("I should see a table containing details of all the available books of the selected age group")
    public void i_should_see_books_by_age_group() {
        assertTrue(searchPage.isResultsTableDisplayed());
    }
    
    @When("I submit the book search entries from Excel")
    public void submitBookSearchEntriesFromExcel() throws InterruptedException {
        // ✅ Open site and go to Search section
        searchPage.openSite();
        searchPage.navigateToSearch();

        String filePath = "src/test/resources/data/AdvancedBookSearchData.xlsx";
        List<Map<String, String>> entries = ExcelReader.getData(filePath, "BookSearch");

        for (Map<String, String> entry : entries) {
            String author = entry.get("Author");
            String subject = entry.get("Subject");
            String edition = entry.get("Edition");
            String format = entry.get("Format");
            String ageGroup = entry.get("AgeGroup");
            String expectedResult = entry.get("ExpectedResult");

            // Fill form fields
            if (author != null && !author.isEmpty()) searchPage.enterAuthor(author);
            if (subject != null && !subject.isEmpty()) searchPage.enterSubject(subject);
            if (edition != null && !edition.isEmpty()) searchPage.selectEdition(edition);
            if (format != null && !format.isEmpty()) searchPage.selectBookFormat(format);
            if (ageGroup != null && !ageGroup.isEmpty()) searchPage.selectAgeGroup(ageGroup);

            searchPage.clickSubmit();
            Thread.sleep(1000);

            if (expectedResult.equalsIgnoreCase("success")) {
                Assert.assertTrue(searchPage.isResultsTableDisplayed(), "Expected results table not shown!");
            } else if (expectedResult.equalsIgnoreCase("failure")) {
                Assert.assertTrue(searchPage.isErrorMessageDisplayedNoBooksFound(), "Expected failure message not shown!");
            }
        }
    }




}
