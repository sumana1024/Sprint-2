package com.LibraryManagement.cucumber.Setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSetup {  // DO NOT CHANGE THE CLASS NAME
    
    private static WebDriver driver;
    
    public static WebDriver getWebDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE
	
<<<<<<< HEAD
	    System.setProperty("webdriver.gecko.driver", "D:/CTS_Eclipse_plugin/geckodriver-v0.36.0-win64/geckodriver.exe");
		driver = new FirefoxDriver();
=======
    	System.setProperty("webdriver.gecko.driver", "path_to_geckodriver.exe");		
    	driver = new FirefoxDriver();
>>>>>>> branch 'main' of git@github.com:sumana1024/Sprint-2.git
	    return driver;
	}
}
