package com.LibraryManagement.cucumber.stepDefinition;


import io.cucumber.java.en.*;
import org.junit.Assert;
import com.LibraryManagement.cucumber.pages.*;

public class ServiceSteps {

    ServicePage servicePage = new ServicePage();

    @Given("I open the site and navigate to the Services page")
    public void openSiteAndNavigate() {
        servicePage.openSite();
        servicePage.goToServicesPage();
    }

    @When("I select the {string} option")
    public void selectOption(String option) {
        servicePage.selectServiceOption(option);
    }

    @When("I enter email id {string}")
    public void enterEmailId(String email) {
        servicePage.enterEmail(email);
    }

    @When("I leave the email id field blank")
    public void leaveEmailBlank() {
        servicePage.enterEmail(""); // blank
    }

    @When("I enter phone number {string}")
    public void enterPhoneNumber(String phone) {
        servicePage.enterPhone(phone);
    }

    @When("I leave the phone number field blank")
    public void leavePhoneBlank() {
        servicePage.enterPhone(""); // blank
    }

    @When("I enter user name {string}")
    public void enterUserName(String name) {
        servicePage.enterName(name);
    }

    @When("I leave the name field blank")
    public void leaveNameBlank() {
        servicePage.enterName(""); // blank
    }
    @When("I enter email query {string}")
    public void enterQueryemailstep(String querymail) {
        servicePage.enterQueryemail(querymail);
    }
    
    @When("I enter chat query {string}")
    public void enterQuerychatstep(String querychat) {
        servicePage.enterQuerychat(querychat);
    }
    @When("I click the submit button")
    public void clickSubmitButton() throws InterruptedException {
        servicePage.clickSubmit();
    }

    @Then("Only one option should remain selected")
    public void validateOnlyOneOption() {
        int selectedCount = servicePage.getSelectedOptionCount();
        Assert.assertEquals("Only one option should be selected", 1, selectedCount);
    }

    @Then("I should see a successful email validation message")
    public void verifySuccessEmailMessage() {
        Assert.assertTrue("Success message not displayed", servicePage.isSuccessMessageDisplayed());
    }

    @Then("I should see an error message for invalid email")
    public void verifyInvalidEmailMessage() {
        Assert.assertEquals("Invalid email message not displayed", servicePage.isInvalidEmailMessageDisplayed());
    }

    @Then("I should see an error message for blank email")
    public void verifyBlankEmailMessage() {
        Assert.assertTrue("Blank email message not displayed", servicePage.isBlankEmailMessageDisplayed());
    }

    @Then("I should see a successful phone validation message")
    public void verifySuccessPhoneMessage() {
        Assert.assertEquals("Message Sent Successfully", servicePage.isPhoneSuccessMessageDisplayed());
    }

    @Then("I should see an error message for invalid phone")
    public void verifyInvalidPhoneMessage() {
        Assert.assertEquals("Invalid phone message not displayed", servicePage.isInvalidPhoneMessageDisplayed());
    }

    @Then("I should see an error message for blank phone number")
    public void verifyBlankPhoneMessage() {
        Assert.assertTrue("Blank phone message not displayed", servicePage.isBlankPhoneMessageDisplayed());
    }

    @Then("I should see a successful name validation message")
    public void verifySuccessNameMessage() {
        Assert.assertEquals("Message Sent Successfully", servicePage.isPhoneSuccessMessageDisplayed());
    }

    @Then("I should see an error message for invalid name")
    public void verifyInvalidNameMessage() {
        Assert.assertEquals("Invalid name message not displayed", servicePage.isInvalidNameMessageDisplayed());
    }

    @Then("I should see an error message for blank name")
    public void verifyBlankNameMessage() {
        Assert.assertTrue("Blank name message not displayed", servicePage.isBlankNameMessageDisplayed());
    }
}

