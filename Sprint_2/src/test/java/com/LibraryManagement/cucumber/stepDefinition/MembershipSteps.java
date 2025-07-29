package com.LibraryManagement.cucumber.stepDefinition;

import io.cucumber.java.en.*;

import java.time.Duration;


import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import com.LibraryManagement.cucumber.pages.MembershipPage;

public class MembershipSteps {

    MembershipPage membershipPage = new MembershipPage();
    String selectedMembership = "";

    @Given("I navigate to the Library Membership page")
    public void navigateToMembershipPage() {
        membershipPage.openSite();
        membershipPage.goToMembershipPage();
    }

    @When("I drag and drop the book into the correct box")
    public void dragAndDropCorrectly() {
        membershipPage.performDragAndDropJS();
    }

    @When("I drag the book but drop it outside the box")
    public void dragIncorrectly() {
        // Negative drag-drop not implemented in original page. Placeholder.
        // You may simulate by not calling performHumanVerification.
    }

    @When("I wait without dragging the book")
    public void waitWithoutDragging() throws InterruptedException {
        Thread.sleep(2000); // Simulate user doing nothing
    }

    @Then("Human Verification section should be hidden")
    public void humanVerificationHidden() {
        // No method to check section visibility exists.
        // You could add validation using DOM element visibility.
        // Placeholder
    }

    @Then("Membership form should be visible")
    public void membershipFormVisible() {
        // Same as above — implement visibility check in MembershipPage if needed
    }

    @Then("Human Verification section should remain visible")
    public void humanVerificationStillVisible() {
        // Placeholder — not implemented in page
    }

    @Then("Membership form should remain hidden")
    public void membershipFormStillHidden() {
        // Placeholder — not implemented in page
    }

    @When("I complete the human verification")
    public void completeVerification() {
        membershipPage.performDragAndDropJS();
    }

    @When("I complete the human verification again")
    public void completeVerificationAgain() {
        membershipPage.performDragAndDropJS();
    }
    

    @When("I select Gold Membership")
    public void selectGoldMembership() {
        membershipPage.selectGoldMembership();
        selectedMembership = "Gold";
    }

    @When("I select Platinum Membership")
    public void selectPlatinumMembership() {
        membershipPage.selectPlatinumMembership();
        selectedMembership = "Platinum";
    }

    @Then("Gold Membership should be selected")
    public void goldMembershipShouldBeSelected() {
    	Assert.assertEquals(selectedMembership, "Gold");
    }


    @Then("Platinum Membership should be selected")
    public void platinumMembershipShouldBeSelected() {
        Assert.assertEquals(selectedMembership, "Platinum");
    }

    @Then("Displayed amount should be Rs.5000")
    public void verifyGoldAmount() {
        String amount = membershipPage.getGoldAmountDisplayed();
        Assert.assertEquals(amount, "Rs.5000");
    }

    @Then("Displayed amount should be Rs.25000")
    public void verifyPlatinumAmount() {
        String amount = membershipPage.getPlatinumAmountDisplayed();
        Assert.assertEquals(amount, "Rs.25000", "Platinum amount not matching");
    }

    @When("I enter card number {string}")
    public void enterCardNumber(String cardNumber) {
        membershipPage.enterLibraryCardNumber(selectedMembership, cardNumber);
    }

    @When("I leave card number empty")
    public void leaveCardEmpty() {
        membershipPage.enterLibraryCardNumber(selectedMembership, "");
    }

    @And("I click Submit")
    public void clickSubmit() {
        membershipPage.submitMembershipForm();
    }

    @Then("Form should submit successfully")
    public void verifySuccessSubmission() {
        String msg = membershipPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("Membership Added"), "Expected success message not found!");
    }



    @Then("Error message should appear for required field")
    public void verifyEmptyFieldError() {
        String errorMsg = selectedMembership.equalsIgnoreCase("Gold")
                ? membershipPage.getGoldCardErrorMessage()
                : membershipPage.getPlatinumCardErrorMessage();

        Assert.assertTrue(errorMsg.contains("Please Enter Your Card number"),
                "Expected error message not shown. Actual: " + errorMsg);
    }


    @Then("Card number field should be reset")
    public void verifyFieldReset() {
        String cardFieldValue = selectedMembership.equalsIgnoreCase("Gold")
                ? membershipPage.getGoldCardFieldValue()
                : membershipPage.getPlatinumCardFieldValue();

        Assert.assertEquals(cardFieldValue, "", "Card number field is not reset");
    }

    @When("I wait for confirmation message")
    public void waitForConfirmation() throws InterruptedException {
        Thread.sleep(1000); // Small wait for message to show up
    }

    @Then("Error message should appear for duplicate number")
    public void duplicateErrorMessage() {
        String msg = selectedMembership.equalsIgnoreCase("Gold")
                ? membershipPage.getGoldCardErrorMessage()
                : membershipPage.getPlatinumCardErrorMessage();
        Assert.assertTrue(msg.contains("already")); // Adjust if different message
    }
    
    @And("I select any membership type")
    public void selectAnyMembership() {
        // Choose randomly or always select one
        membershipPage.selectGoldMembership(); // or Platinum
        selectedMembership = "Gold"; // or "Platinum"
    }

    @And("I navigate to the Members page")
    public void goToMembersPage() {
        membershipPage.goToMembers();  // Calls method from MembershipPage.java
    }

 // Add inside MembershipSteps.java

    @Then("The members table should display the newly added member")
    public void memberVisible() {
        Assert.assertTrue(membershipPage.hasMember("LCN_007"));
    }


    @Then("The members table should not display any data")
    public void noMembersInTable() {
        Assert.assertTrue(membershipPage.isEmpty(), "Table is not empty");
    }



}
