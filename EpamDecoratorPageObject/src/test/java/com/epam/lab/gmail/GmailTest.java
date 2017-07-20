package com.epam.lab.gmail;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.lab.gmail.drivers.BrowserDriver;
import com.epam.lab.gmail.models.Message;
import com.epam.lab.gmail.pages.GmailLoginPage;
import com.epam.lab.gmail.pages.GmailMainPage;
import com.epam.lab.gmail.prop.PropertiesLoader;

public class GmailTest {
    public static final String PROPERTIES_FILE_URL = "resources/config.properties";

    public static Logger logger = Logger.getLogger(GmailTest.class);

    private WebDriver driver;
    private GmailMainPage mainPage;
    private List<Message> markedMessagesModelsList;

    @BeforeClass
    public void setUp() throws Exception {
	logger.info("setUp");
	PropertiesLoader.load(PROPERTIES_FILE_URL);
	this.driver = BrowserDriver.getInstance();
    }

    @Test
    public void login() {

    }

    @Test(dependsOnMethods = { "login" })
    public void markMessagesToImportant() {
	GmailLoginPage loginPage = new GmailLoginPage(driver);
	
	mainPage = loginPage.open().loginsAs(System.getProperty("login"), System.getProperty("password"));
	
	markedMessagesModelsList = mainPage.markMessagesAsImportant(3);

	mainPage.navigationMenu().importent();

	assertTrue(mainPage.getMessagesModels().containsAll(markedMessagesModelsList));

	mainPage.markMessagesByModels(markedMessagesModelsList);

	mainPage.topEditMenu().clickDelete();

	List<Message> mainPageMessages = mainPage.getMessagesModels();

	for (Message messageModel : this.markedMessagesModelsList) {
	    assertFalse(mainPageMessages.contains(messageModel));
	}

    }

    @AfterClass
    public void close() {
	driver.quit();
    }
}
