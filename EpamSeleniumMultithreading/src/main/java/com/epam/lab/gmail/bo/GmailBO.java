package com.epam.lab.gmail.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.gmail.GmailTest;
import com.epam.lab.gmail.drivers.DriverSingltone;
import com.epam.lab.gmail.models.Message;
import com.epam.lab.gmail.pages.GmailMainPage;

public class GmailBO {
	public static Logger logger = Logger.getLogger(GmailTest.class);
	private GmailMainPage mainPage;

	public GmailBO() {
		mainPage = new GmailMainPage(DriverSingltone.getInstance());
	}

	public List<Message> getMassages() {
		return mainPage.getMessagesModels();
	}

	public List<Message> markMessagesAsImportant(int messagesNumber) {
		return mainPage.markMessagesAsImportant(messagesNumber);
	}

	public void openImportantMesssagesList() {
		logger.info("openImportantMesssagesList metod --------------");
		mainPage.navigationMenu().clikOnMore();
		mainPage.navigationMenu().clikOnImportant();

	}

	public void deleteMessages(List<Message> listToDelete) {
		mainPage.markMessagesByModels(listToDelete);
		mainPage.topEditMenu().clickDelete();
	}

	public boolean arePresent(List<Message> messagesList) {
		List<Message> presentMessagesList = mainPage.getMessagesModels();
		boolean isPresent = false;
		for (Message message : messagesList) {
			isPresent = presentMessagesList.contains(message);
			if (isPresent) {
				break;
			}
		}
		return isPresent;
	}

}
