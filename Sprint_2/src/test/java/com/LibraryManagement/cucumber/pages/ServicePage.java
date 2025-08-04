package com.LibraryManagement.cucumber.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.LibraryManagement.cucumber.Setup.DriverSetup;


public class ServicePage {

    WebDriver driver;

    public ServicePage() {
        
        driver = DriverSetup.getWebDriver();
       
    }

    public void openSite() {
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Library.html");
        driver.manage().window().maximize();
        
    }

    public void goToServicesPage() {
        // Click or navigate to services page
        driver.findElement(By.linkText("Services")).click();
    }

    public void selectServiceOption(String option) {
        // Example: handle based on option text
        switch(option.toLowerCase()) {
            case "email":
                driver.findElement(By.id("medium_email")).click();
                break;
            case "call":
                driver.findElement(By.id("medium_call")).click();
                break;
            case "chat":
                driver.findElement(By.id("medium_chat")).click();
                break;
        }
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@id='fromEmail']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPhone(String phone) {
        WebElement phoneField = driver.findElement(By.cssSelector("#yourphone"));
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void enterName(String name) {
        WebElement nameField = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/form[1]/div[4]/div[3]/label[1]/input[1]"));
        nameField.clear();
        nameField.sendKeys(name);
    }
    public void enterQueryemail(String queryText) {
        WebElement queryField = driver.findElement(By.id("queryemail")); 
        queryField.clear();
        queryField.sendKeys(queryText);
    }
    public void enterQuerychat(String queryText) {
        WebElement queryField1 = driver.findElement(By.xpath("//label/textarea[contains(@id,'chat')]")); 
        queryField1.clear();
        queryField1.sendKeys(queryText);
    }
    public void clickSubmit() throws InterruptedException {
        driver.findElement(By.id("QuerySubmit")).click();
       Thread.sleep(3000);
    }

    
    public int getSelectedOptionCount() {
        int count = 0;
        if(driver.findElement(By.id("medium_email")).isSelected()) count++;
        if(driver.findElement(By.id("medium_call")).isSelected()) count++;
        if(driver.findElement(By.id("medium_chat")).isSelected()) count++;
        return count;
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(By.id("mediummailoutput")).isDisplayed();
    }
    public String isPhoneSuccessMessageDisplayed() {
        String calloutput=driver.findElement(By.id("mediumchatoutput")).getText();
        System.out.println(calloutput);
        return calloutput;
    }

    public String isInvalidEmailMessageDisplayed() {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mediummailoutput")));
        return errorMsg.getText();
    }

    public boolean isBlankEmailMessageDisplayed() {
        return driver.findElement(By.id("fromemailError")).isDisplayed();
    }

    public String isInvalidPhoneMessageDisplayed() {
        return driver.findElement(By.id("mediumchatoutput")).getText();
    }

    public boolean isBlankPhoneMessageDisplayed() {
        return driver.findElement(By.id("yourphoneError")).isDisplayed();
    }

    public String isInvalidNameMessageDisplayed() {
        return driver.findElement(By.id("mediumchatoutput")).getText();
    }

    public boolean isBlankNameMessageDisplayed() {
        return driver.findElement(By.id("chatnameError")).isDisplayed();
    }
}

