package com.LibraryManagement.cucumber.stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import com.LibraryManagement.cucumber.pages.*;

import java.util.List;
import java.util.Map;

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

    @When("I select the following service options:")
    public void selectMultipleOptions(DataTable optionsTable) {
        List<String> options = optionsTable.asList();
        for (String option : options) {
            servicePage.selectServiceOption(option);
        }
    }

    // Email form using DataTable
    @When("I fill the email form with the following data:")
    public void fillEmailForm(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String email = data.get(0).getOrDefault("Email ID","");
        String query = data.get(0).getOrDefault("Query","");

        servicePage.enterEmail(email== null? "" : email);
        servicePage.enterQueryemail(query== null? "" : query);
    }

    // Chat form using DataTable
    @When("I fill the chat form with the following data:")
    public void fillChatForm(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        String name = data.get(0).getOrDefault("Name", "");
        String phone = data.get(0).getOrDefault("Phone Number", "");
        String query = data.get(0).getOrDefault("Query", "");

        servicePage.enterName(name == null ? "" : name);
        servicePage.enterPhone(phone == null ? "" : phone);
        servicePage.enterQuerychat(query == null ? "" : query);
    }

    @When("I click the submit button")
    public void clickSubmitButton() throws InterruptedException {
        servicePage.clickSubmit();
        Thread.sleep(1000);
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
