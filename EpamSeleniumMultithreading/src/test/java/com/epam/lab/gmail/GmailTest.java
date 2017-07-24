package com.epam.lab.gmail;

import static org.testng.Assert.assertFalse;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.lab.gmail.bo.GmailBO;
import com.epam.lab.gmail.bo.LoginBO;
import com.epam.lab.gmail.drivers.DriverSingltone;
import com.epam.lab.gmail.models.Message;
import com.epam.lab.gmail.models.User;
import com.epam.lab.gmail.prop.PropertiesLoader;

public class GmailTest {
	public static final String PROPERTIES_FILE_URL = "resources/config.properties";
	public static User user;

	public static Logger logger = Logger.getLogger(GmailTest.class);

	@BeforeClass
	public void setUp() throws Exception {
		logger.info("setUp");
		PropertiesLoader.load(PROPERTIES_FILE_URL);
		user = new User(System.getProperty("login"), System.getProperty("password"));
	}
	
	

	@Test(threadPoolSize=2,invocationCount = 2)
	public void markMessagesToImportantAndDelete() {
	    	System.out.println(DriverSingltone.getInstance());
		LoginBO loginBO = new LoginBO();
		loginBO.loginAs(user);

		GmailBO gmailBo = new GmailBO();

		List<Message> markedMessagesList = gmailBo.markMessagesAsImportant(2);

		

		gmailBo.openImportantMesssagesList();

		gmailBo.deleteMessages(markedMessagesList);

		assertFalse(gmailBo.arePresent(markedMessagesList));
	}

	@AfterClass
	public void close() {
	    for(WebDriver driver : DriverSingltone.getDriversPool()) {
		    driver.quit();
		}
	}
}
