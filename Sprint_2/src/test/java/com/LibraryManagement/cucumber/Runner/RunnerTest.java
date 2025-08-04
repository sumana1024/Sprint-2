package com.LibraryManagement.cucumber.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// CucumberOptions to define features, glue (step definitions) and plugins
@CucumberOptions(
    features = "src/test/java/com/LibraryManagement/cucumber/features",   // path to .feature files
    glue = {
    		"com.LibraryManagement.cucumber.stepDefinition",   
    		"com.LibraryManagement.cucumber.Setup",
    		"com.LibraryManagement.cucumber.hooks"
    },
    plugin = {
        "pretty",
        "junit:target/cucumber-reports/Cucumber.xml",
        "html:target/cucumber-reports/html-report.html",
        "json:target/cucumber-reports/report.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"

    },

    tags = "(@AdvanceBookSearch or @Membership or @ServiceQuery or @BookSearch) and (not @ignore)" // optional filtering

)
public class RunnerTest extends AbstractTestNGCucumberTests {
    // No code needed here, TestNG will run with the above configurations
}

