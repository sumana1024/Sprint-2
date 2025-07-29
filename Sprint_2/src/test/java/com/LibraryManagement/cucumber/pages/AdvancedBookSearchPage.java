package com.LibraryManagement.cucumber.pages;

import com.LibraryManagement.cucumber.Setup.DriverSetup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdvancedBookSearchPage {
    WebDriver driver;

    public AdvancedBookSearchPage() {
    	driver = DriverSetup.getWebDriver();
    }
    
    public WebDriver getDriver() {
        return driver;
    }

    public void openSite() {
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Library.html");
        driver.manage().window().maximize();
    }
    
    public void navigateToSearch() {
    	 driver.findElement(By.id("searchlink")).click();
    }

    public void enterAuthor(String author) {
        driver.findElement(By.id("authorName")).sendKeys(author);
    }

    public void enterSubject(String subject) {
        driver.findElement(By.id("subject")).sendKeys(subject);
    }

    public void selectEdition(String edition) {
        new Select(driver.findElement(By.id("edition"))).selectByVisibleText(edition);
    }

    public void selectBookFormat(String format) {
        new Select(driver.findElement(By.id("format"))).selectByVisibleText(format);
    }

    public void selectAgeGroup(String group) {
        driver.findElement(By.cssSelector("input[name='ageGroup'][value='" +group+ "']")).click();
    }

    public void clickSubmit() {
        driver.findElement(By.id("searchSubmit")).click();
    }

    public boolean isResultsTableDisplayed() {
    	//WebDriverWait wait = new WebDriverWait(driver, 5); // 5 seconds
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    	    try 
    	    {
    	        WebElement table=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bookscontainer table")));
    	        return table.isDisplayed();
    	    } 
    	    catch (TimeoutException e) 
    	    {
    	        System.out.println("=====Test failed: Table not displayed after form submission.=====");
    	        return false;
    	    }
    }

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
    
    public Map<String, String> getDisplayedBookDetails() {
        Map<String, String> bookDetails=new HashMap<>();

        // Author
        String author=driver.findElement(By.xpath("//td[text()='Author']/following-sibling::td")).getText();
        // Edition
        String edition=driver.findElement(By.xpath("//td[text()='Edition']/following-sibling::td")).getText();
        // Subject
        String subject=driver.findElement(By.xpath("//td[text()='Subject']/following-sibling::td")).getText();
        // Format
        String format=driver.findElement(By.xpath("//td[text()='Format']/following-sibling::td")).getText();
        // Age
        String age=driver.findElement(By.xpath("//td[text()='Age']/following-sibling::td")).getText();

        bookDetails.put("Author", author);
        bookDetails.put("Edition", edition);
        bookDetails.put("Subject", subject);
        bookDetails.put("Format", format);
        bookDetails.put("Age", age);

        return bookDetails;
    }
    
    public boolean isErrorMessageDisplayedNoBooksFound() {
        
    	// Step 1: Capture current DOM state
        List<WebElement> before = driver.findElements(By.xpath("//*"));

        // Step 2: Wait up to 5 seconds for new elements
        //WebDriverWait wait=new WebDriverWait(driver, 5);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean newElementsAppeared=false;

        try 
        {
            newElementsAppeared=wait.until(driver1 ->driver1.findElements(By.xpath("//*")).size() > before.size());
        } 
        catch (Exception e) 
        {
            newElementsAppeared=false;  // Timeout or error
        }

        // Step 3: Compare if any new elements appeared
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
