package com.epam.lab.gmail.drivers;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserDriver {
    private static WebDriver driverInstance = null;
    
    public static WebDriver getInstance() {
	if(Objects.isNull(driverInstance)) {
	    driverInstance = new ChromeDriver();
	    driverInstance.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS).pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	return driverInstance;
    }

}
