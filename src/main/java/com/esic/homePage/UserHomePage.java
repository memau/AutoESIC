package com.esic.homePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 
 * @author Mauli
 *
 *closing the two popups and logging out
 */
public class UserHomePage {
	
	final static Logger logger = Logger.getLogger(UserHomePage.class);
	
	//check for popup once logged in
	@FindBy(xpath="//table[@id='table7']/tbody/tr/td")
	WebElement popupText;
		
	//close popup
	@FindBy(name="div1_close")
	WebElement closePopup;
	
	//second popup leftTopFormLabelgrid
	@FindBy(className="leftTopFormLabelgrid")
	WebElement popupIPText;
	
	//close the second popup
	@FindBy(id="btnClose")
	WebElement closeIPPopup;
	
	//logout
	@FindBy(id="imgbtnLogout")
	WebElement logoutButton;
		
	public void closePopupForSession() throws ClosePopupException{
		//check for popup
		validatePopup1(popupText.getText());
		closePopup.click();
		validatePopup2(popupIPText.getText());
		closeIPPopup.click();
	}
	
	public void logout()throws LogoutError{
		logger.info("Logging out of the application");
		logoutButton.click();
	}
	
	private void validatePopup1(String text){
		logger.info(text);
		String popup1="Either last logged-in session was not closed/logged-out";
		if(text.contains(popup1)){
			logger.info("Success Scenario: First popup is valid");
		}
	}
	
	private void validatePopup2(String text){
		logger.info(text);
		String popup2="IP NUMBER ONCE GIVEN IS VALID FOR THE LIFETIME OF THE EMPLOYEE.";
		if(text.contains(popup2)){
			logger.info("Success Scenario: Second popup is valid");
		}
	}
	
	class LogoutError extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	class ClosePopupException extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
	
	
}
