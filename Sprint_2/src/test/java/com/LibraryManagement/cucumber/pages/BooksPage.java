package com.LibraryManagement.cucumber.pages;

import com.LibraryManagement.cucumber.Setup.DriverSetup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BooksPage {

    WebDriver driver;

    public BooksPage(WebDriver driver) {
        this.driver = driver;
    }

    private By booksSection = By.id("bookslink"); // adjust if necessary
    private By searchBox = By.id("searchbox"); // adjust if necessary
    private By bookRows = By.cssSelector("table tbody tr");
    private By noBooksMessage = By.xpath("//*[contains(text(),'No books found')]");
    private By searchBarLabel = By.cssSelector("label[for='searchBox']");
    private By resetButton = By.id("resetBtn"); // adjust if needed

    public void clickBooksSection() {
        driver.findElement(booksSection).click();
    }

    public void enterBookInSearchBox(String bookName) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(bookName);
    }

    public void clearSearchBox() {
        driver.findElement(searchBox).clear();
    }

    public boolean isBookDisplayed(String bookName) {
        List<WebElement> rows = driver.findElements(bookRows);
        for (WebElement row : rows) {
            if (row.getText().contains(bookName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNoBooksFoundMessageDisplayed() {
        return driver.findElements(noBooksMessage).size() > 0;
    }

    public boolean isSearchBarLabelCorrect() {
        return driver.findElement(searchBarLabel)
                     .getText()
                     .equalsIgnoreCase("Search by Book Name or Author");
    }

    public void clickResetButton() {
        driver.findElement(resetButton).click();
    }

    public boolean isFullBookListDisplayed() {
        List<WebElement> rows = driver.findElements(bookRows);
        return rows.size() > 1;
    }
}

