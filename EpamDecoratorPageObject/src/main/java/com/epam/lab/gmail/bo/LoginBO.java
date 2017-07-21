package com.epam.lab.gmail.bo;

import com.epam.lab.gmail.drivers.BrowserDriver;
import com.epam.lab.gmail.models.User;
import com.epam.lab.gmail.pages.GmailLoginPage;

public class LoginBO {
    private GmailLoginPage loginPage;
    
    public void loginAs(User user) {
	loginPage = new GmailLoginPage(BrowserDriver.getInstance());
	loginPage.open().loginsAs(user.getLogin(),user.getPassword());
    }

}
