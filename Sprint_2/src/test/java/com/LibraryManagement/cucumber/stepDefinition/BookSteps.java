package com.LibraryManagement.cucumber.stepDefinition;

import com.LibraryManagement.cucumber.pages.BooksPage;
import com.LibraryManagement.cucumber.Setup.DriverSetup;
import io.cucumber.java.en.*;
//import org.junit.Assert;
import org.testng.Assert;
import static org.testng.Assert.assertTrue; 
import org.openqa.selenium.WebDriver;



public class BookSteps {

    WebDriver driver = DriverSetup.getWebDriver();
    BooksPage booksPage = new BooksPage(driver);

    @Given("User navigates to the Library Management Application")
    public void user_navigates_to_the_library_management_application() {
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Library.html"); 
    }

    @When("User clicks on the {string} section")
    public void userClicksOnSection(String section) {
        if (section.equalsIgnoreCase("Books")) {
            booksPage.clickBooksSection();
        }
    }

    @When("User enters {string} into the search box")
    public void enterTextIntoSearchBox(String bookName) {
        booksPage.enterBookInSearchBox(bookName);
    }

    @And("User clears the search box")
    public void userClearsSearchBox() {
        booksPage.clearSearchBox();
    }

    @And("User refreshes the page")
    public void userRefreshesThePage() {
        driver.navigate().refresh();
    }

    @Then("Complete list of all available books should reappear")
    public void verifyAllBooksReappear() {
        Assert.assertTrue("Book list should reappear", booksPage.isFullBookListDisplayed());
    }

    @Given("User navigates to the Books page")
    public void userNavigatesToBooksPage() {
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Books.html");
        booksPage.clickBooksSection();
    }

    @Then("The correct book matching the entered name should be displayed")
    public void verifyBookIsDisplayed() {
        Assert.assertTrue("Book should be displayed", booksPage.isBookDisplayed("Letting Go"));
    }

    @When("User enters a nonexistent book name into the search box")
    public void userEntersNonexistentBook() {
        booksPage.enterBookInSearchBox("nonexistentbook");
    }

    @Then("System should display a message {string}")
    public void systemDisplaysNoBookFoundMessage(String expectedMessage) {
        Assert.assertTrue("No books found message should be displayed", booksPage.isNoBooksFoundMessageDisplayed());
    }

    @Then("The search bar should clearly indicate {string}")
    public void searchBarShouldIndicateLabel(String expectedLabel) {
        Assert.assertTrue("Search label is correct", booksPage.isSearchBarLabelCorrect());
    }

    @And("User clicks the Reset button")
    public void userClicksResetButton() {
        booksPage.clickResetButton();
    }

    @Then("All input fields should be cleared and complete list of books displayed")
    public void verifyResetClearsFieldsAndRestoresBooks() {
        Assert.assertTrue("Books list should be visible after reset", booksPage.isFullBookListDisplayed());
    }
}
