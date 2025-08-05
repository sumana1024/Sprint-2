package com.LibraryManagement.cucumber.pages;

import com.LibraryManagement.cucumber.Setup.DriverSetup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdvancedBookSearchPage {
    WebDriver driver;

    public AdvancedBookSearchPage() {
    	driver = DriverSetup.getWebDriver();
    }
    
    public WebDriver getDriver() {
        return driver;
    }

    //Opens the Library application site and maximizes the browser window.
    public void openSite() {
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Library.html");
        driver.manage().window().maximize();
    }
    
    //Navigates to the "Search" section by clicking the search link.
    public void navigateToSearch() {
    	 driver.findElement(By.id("searchlink")).click();
    }

    //Enters the author's name in the Author Name field.
    public void enterAuthor(String author) {
    	WebElement authorName= driver.findElement(By.id("authorName"));
    	authorName.clear();
        authorName.sendKeys(author);
    }

    //Enters the book subject in the Subject field.
    public void enterSubject(String subject) {
        WebElement subjectField=driver.findElement(By.id("subject"));
        subjectField.clear();
        subjectField.sendKeys(subject);
    }

    //Selects an edition from the Edition dropdown list.
    public void selectEdition(String edition) {
        new Select(driver.findElement(By.id("edition"))).selectByVisibleText(edition);
    }

    //Selects a format from the Book Format dropdown list.
    public void selectBookFormat(String format) {
        new Select(driver.findElement(By.id("format"))).selectByVisibleText(format);
    }

    //Selects an age group from the Age Group radio buttons.
    public void selectAgeGroup(String group) {
        driver.findElement(By.cssSelector("input[name='ageGroup'][value='" +group+ "']")).click();
    }

    //Clicks the Submit button to perform the search.
    public void clickSubmit() {
        driver.findElement(By.id("searchSubmit")).click();
    }

    //Waits for and verifies if the results table is displayed after form submission.
    public boolean isResultsTableDisplayed() {
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	    try 
	    {
	        WebElement table=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bookscontainer table")));
	        System.out.println("=====Test passed: Table displayed successfully.=====");
	        return table.isDisplayed();
	    } 
	    catch (TimeoutException e) 
	    {
	        System.out.println("=====Test failed: Table not displayed after form submission.=====");
	        return false;
	    }
    }

    //Verifies that all mandatory field error messages are displayed when fields are left blank.
    public boolean isErrorMessageDisplayed() {
      
        //error message for author name field
        boolean isAuthorErrorDisplayed=driver.findElement(By.id("authorNameError")).isDisplayed();
        //error message for subject field
        boolean isSubjectErrorDisplayed=driver.findElement(By.id("subjectError")).isDisplayed();
        //error message for edition field
        boolean isEditionErrorDisplayed=driver.findElement(By.id("editionError")).isDisplayed();
        //error message for book format field
        boolean isFormatErrorDisplayed=driver.findElement(By.id("formatError")).isDisplayed();
        //error message for age group field
        boolean isAgeGroupErrorDisplayed=driver.findElement(By.id("ageGroupError")).isDisplayed();

        return isAuthorErrorDisplayed &&
               isSubjectErrorDisplayed &&
               isEditionErrorDisplayed &&
               isFormatErrorDisplayed &&
               isAgeGroupErrorDisplayed;
    }
    
    //Extracts and returns the displayed book details from the result table.
    public Map<String, String> getDisplayedBookDetails() {
        Map<String, String> bookDetails=new HashMap<>();

        //Capture book detail values from the table
        // Author
        String author=driver.findElement(By.xpath("//td[text()='Author']/following-sibling::td")).getText();
        //Edition
        String edition=driver.findElement(By.xpath("//td[text()='Edition']/following-sibling::td")).getText();
        //Subject
        String subject=driver.findElement(By.xpath("//td[text()='Subject']/following-sibling::td")).getText();
        //Format
        String format=driver.findElement(By.xpath("//td[text()='Format']/following-sibling::td")).getText();
        //Age Group
        String age=driver.findElement(By.xpath("//td[text()='Age']/following-sibling::td")).getText();

        //Store values in the map
        bookDetails.put("Author", author);
        bookDetails.put("Edition", edition);
        bookDetails.put("Subject", subject);
        bookDetails.put("Format", format);
        bookDetails.put("Age", age);

        return bookDetails;
    }
    
    //Detects whether an error message or any UI change is triggered when a non-existent book is searched.
    public boolean isErrorMessageDisplayedNoBooksFound() {
        
    	//Capture the initial state of the DOM
        List<WebElement> before = driver.findElements(By.xpath("//*"));

        //Wait up to 5 seconds for new elements
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean newElementsAppeared=false;

        try 
        {
            newElementsAppeared=wait.until(driver1 ->driver1.findElements(By.xpath("//*")).size() > before.size()); //Wait for DOM to change 
        } 
        catch (Exception e) 
        {
            newElementsAppeared=false; //Timeout or error
        }

        //Compare if any new elements appeared
        List<WebElement> after=driver.findElements(By.xpath("//*"));
        int newElementsCount=after.size() - before.size();

        if (newElementsAppeared && newElementsCount > 0) 
        {
            System.out.println("New elements appeared: "+newElementsCount);
            return true;
        } 
        else 
        {
            System.out.println("=====Test failed: No error message displayed after searching non-existent book.=====");
            return false;
        }
    }
}
