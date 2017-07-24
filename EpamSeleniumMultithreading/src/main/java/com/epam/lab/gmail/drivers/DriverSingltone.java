package com.epam.lab.gmail.drivers;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingltone {
    private static Logger logger = Logger.getLogger(DriverSingltone.class);

    private static List<WebDriver> driverPool = new ArrayList<>();
    private static ThreadLocal<DriverSingltone> instance = new ThreadLocal<DriverSingltone>();
    private WebDriver driver;

    public static synchronized WebDriver getInstance() {
	logger.info("getInstance method");
	
	if (null == instance.get()) {
	    try {
		instance.set(new DriverSingltone());
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    }
	}
	return instance.get().getDriver();
    }

    public static List<WebDriver> getDriversPool() {
	logger.info("getDriversPool method");
	return driverPool;
    }

    protected DriverSingltone() throws MalformedURLException {
	logger.info("getDriversPool method");
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS).pageLoadTimeout(10, TimeUnit.SECONDS);
	driverPool.add(driver);

    }

    public WebDriver getDriver() {
	return this.driver;
    }
}
