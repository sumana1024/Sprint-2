package com.LibraryManagement.cucumber.Setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSetup {  // DO NOT CHANGE THE CLASS NAME
    
    private static WebDriver driver;
    
    public static WebDriver getWebDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE
	
    	System.setProperty("webdriver.gecko.driver", "C:\\Users\\DELL\\Downloads\\geckodriver-v0.36.0-win64\\geckodriver.exe");		
    	driver = new FirefoxDriver();
    	Hooks.driver = driver;
	    return driver;
	}
}
