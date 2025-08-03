package com.LibraryManagement.cucumber.pages;

import java.time.Duration;
import java.util.List;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.LibraryManagement.cucumber.Setup.DriverSetup;

public class MembershipPage {

    WebDriver driver;

    public MembershipPage() {
        driver = DriverSetup.getWebDriver();
    }
    
    public WebDriver getDriver() {
        return driver;
    }


    public void openSite() {
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Library.html");
        driver.manage().window().maximize();
    }

    public void goToMembershipPage() {
        driver.findElement(By.linkText("Membership")).click();
    }

//    public void performHumanVerification() {
//        WebElement drag = driver.findElement(By.id("drag1"));
//        WebElement drop = driver.findElement(By.id("div1"));
//
//        Actions actions = new Actions(driver);
//        actions.dragAndDrop(drag, drop).build().perform();
//    }
    
    public void performDragAndDropJS() {
        WebElement source = driver.findElement(By.id("drag1"));
        WebElement target = driver.findElement(By.id("div1"));

        String jsDragDrop = 
            "function simulateDragDrop(sourceNode, destinationNode) {" +
            "  var EVENT_TYPES = { DRAG_END: 'dragend', DRAG_START: 'dragstart', DROP: 'drop' };" +
            "  function createCustomEvent(type) {" +
            "    var event = new CustomEvent('CustomEvent');" +
            "    event.initCustomEvent(type, true, true, null);" +
            "    event.dataTransfer = {" +
            "      data: {}," +
            "      setData: function(type, val) { this.data[type] = val; }," +
            "      getData: function(type) { return this.data[type]; }" +
            "    };" +
            "    return event;" +
            "  }" +
            "  function dispatchEvent(node, type, event) {" +
            "    if (node.dispatchEvent) { return node.dispatchEvent(event); }" +
            "    if (node.fireEvent) { return node.fireEvent('on' + type, event); }" +
            "  }" +
            "  var dragStartEvent = createCustomEvent(EVENT_TYPES.DRAG_START);" +
            "  dispatchEvent(sourceNode, EVENT_TYPES.DRAG_START, dragStartEvent);" +
            "  var dropEvent = createCustomEvent(EVENT_TYPES.DROP);" +
            "  dropEvent.dataTransfer = dragStartEvent.dataTransfer;" +
            "  dispatchEvent(destinationNode, EVENT_TYPES.DROP, dropEvent);" +
            "  var dragEndEvent = createCustomEvent(EVENT_TYPES.DRAG_END);" +
            "  dragEndEvent.dataTransfer = dragStartEvent.dataTransfer;" +
            "  dispatchEvent(sourceNode, EVENT_TYPES.DRAG_END, dragEndEvent);" +
            "}" +
            "simulateDragDrop(arguments[0], arguments[1]);";

        ((JavascriptExecutor) driver).executeScript(jsDragDrop, source, target);
    }

    
    public void selectGoldMembership() {
        try {
            WebElement goldRadio = driver.findElement(By.id("Gold"));

            // Force click using JavaScriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", goldRadio);

            System.out.println("Gold Membership radio button clicked via JS.");
        } catch (Exception e) {
            System.out.println("Error clicking Gold Membership radio via JS: " + e.getMessage());
            throw e;
        }
    }


    
    public void selectPlatinumMembership() {
        try {
            WebElement platinumRadio = driver.findElement(By.id("Platinum"));

            // Force click using JavaScriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", platinumRadio);

            System.out.println("Platinum Membership radio button clicked via JS.");
        } catch (Exception e) {
            System.out.println("Error clicking Platinum Membership radio via JS: " + e.getMessage());
            throw e;
        }
    }
    
    public void enterLibraryCardNumber(String membershipType, String cardNumber) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            if (membershipType.equalsIgnoreCase("Gold")) {
                WebElement cardInput = driver.findElement(By.id("libcardNumberGold"));
                js.executeScript("arguments[0].scrollIntoView(true);", cardInput);
                js.executeScript("arguments[0].value = '';", cardInput); // clear field
                js.executeScript("arguments[0].value = arguments[1];", cardInput, cardNumber); // set value
                System.out.println("Gold card number entered via JS: " + cardNumber);
            } else if (membershipType.equalsIgnoreCase("Platinum")) {
                WebElement cardInput = driver.findElement(By.id("libcardNumberPtm"));
                js.executeScript("arguments[0].scrollIntoView(true);", cardInput);
                js.executeScript("arguments[0].value = '';", cardInput);
                js.executeScript("arguments[0].value = arguments[1];", cardInput, cardNumber);
                System.out.println("Platinum card number entered via JS: " + cardNumber);
            }
        } catch (Exception e) {
            System.out.println("Failed to enter library card number: " + e.getMessage());
            throw e;
        }
    }



    public void submitMembershipForm() {
        try {
            WebElement submitButton = driver.findElement(By.id("memberSubmit"));

            // Use JavaScriptExecutor to click the button
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", submitButton);  // ensure visible
            js.executeScript("arguments[0].click();", submitButton);               // force click

            System.out.println("Submit button clicked using JavaScriptExecutor.");
        } catch (Exception e) {
            System.out.println("Error clicking Submit button: " + e.getMessage());
            throw e;
        }
    }


    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement msgElmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("memberoutput")));
        return msgElmt.getText();
    }


    public String getGoldCardErrorMessage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement err = driver.findElement(By.id("libcardNumberGoldError"));
        return (String) js.executeScript("return arguments[0].textContent;", err);
    }
    
    public String getPlatinumCardErrorMessage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement err = driver.findElement(By.id("libcardNumberPtmError"));
        return (String) js.executeScript("return arguments[0].textContent;", err);
    }



    public void clearGoldCardField() {
        driver.findElement(By.id("libcardNumberGold")).clear();
    }

    public void clearPlatinumCardField() {
        driver.findElement(By.id("libcardNumberPtm")).clear();
    }

    public String getGoldAmountDisplayed() {
        return driver.findElement(By.id("memberGoldAmount")).getAttribute("value");
    }

    public String getPlatinumAmountDisplayed() {
        // Find the platinum container
        WebElement platinumSection = driver.findElement(By.id("memberPlatinum"));
        
        // Now find the input inside that section
        WebElement amountField = platinumSection.findElement(By.xpath(".//input[@type='text' and contains(@value, 'Rs.')]"));
        
        return amountField.getAttribute("value");
    }
    
    public String getSelectedMembership() {
        List<WebElement> radios = driver.findElements(By.name("membershipType"));
        for (WebElement radio : radios) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean isChecked = (Boolean) js.executeScript("return arguments[0].checked;", radio);
            if (isChecked) {
                return radio.getAttribute("value");
            }
        }
        return "";
    }
    
    public String getGoldCardFieldValue() {
        WebElement cardInput = driver.findElement(By.id("libcardNumberGold"));
        return cardInput.getAttribute("value").trim();
    }

    public String getPlatinumCardFieldValue() {
        WebElement cardInput = driver.findElement(By.id("libcardNumberPtm"));
        return cardInput.getAttribute("value").trim();
    }
    
    
    
 // Add inside MembershipPage.java

    public void goToMembers() {
        driver.findElement(By.linkText("Members")).click();
    }

    public boolean hasMember(String card) {
        try {
            WebElement table = driver.findElement(By.id("memberTable"));
            return table.getText().contains(card);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmpty() {
        try {
            WebElement table = driver.findElement(By.id("memberTable"));
            return !table.getText().contains("LCN");
        } catch (Exception e) {
            return true;
        }
    }

    public String url() {
        return driver.getCurrentUrl();
    }

    public int countCardOccurrences(String cardNumber) {
        try {
            WebElement table = driver.findElement(By.id("memberTable"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            int count = 0;
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (!cells.isEmpty() && cells.get(0).getText().equalsIgnoreCase(cardNumber)) {
                    count++;
                }
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    

}
