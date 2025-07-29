package com.LibraryManagement.cucumber.Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// CucumberOptions to define features, glue (step definitions) and plugins
@CucumberOptions(
    features = "src/test/java/com/LibraryManagement/cucumber/features",   // path to .feature files
    glue = "com.LibraryManagement.cucumber.stepDefinition",                   // package containing step definitions
    plugin = {
        "pretty",
        "junit:target/cucumber-reports/Cucumber.xml"
    },

    

    tags = "(@AdvancedBookSearch or @BookSearch or @LibraryCard or @ServiceQuery or @Membership) and (not @ignore)" // optional filtering

)
public class RunnerTest extends AbstractTestNGCucumberTests {
    // No code needed here, TestNG will run with the above configurations
}
