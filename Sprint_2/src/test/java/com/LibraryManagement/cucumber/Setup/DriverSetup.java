package com.LibraryManagement.cucumber.Setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.LibraryManagement.cucumber.hooks.Hooks;


public class DriverSetup {  
    
    private static WebDriver driver;
    
    public static WebDriver getWebDriver() {    
	
    	System.setProperty("webdriver.gecko.driver", "path_to_geckodriver.exe");		

    	driver = new FirefoxDriver();
    	Hooks.driver = driver;

	    return driver;
	}
}
