package com.esic.selenium.processor.update;

import org.apache.log4j.Logger;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.LoginAction;
import com.esic.selenium.action.OpenESICWebsiteAction;
import com.esic.selenium.action.OpenLinkUpdateIPAction;
import com.esic.selenium.action.SearchAndOpenRegisteredESICAction;
import com.esic.selenium.driver.ESICFireFoxWebDriver;
import com.esic.selenium.processor.ESICRecordProcessorBase;

public class ESICRecordUpdater extends ESICRecordProcessorBase {
	
	ESICFireFoxWebDriver driver;
	
	final static Logger logger = Logger
			.getLogger(ESICRecordUpdater.class);
	
	
	public void processRecord(ESICRecord record) {
		ESICFireFoxWebDriver driver = loadDriver();
		
		
		OpenESICWebsiteAction action = new OpenESICWebsiteAction();
		action.perform(driver, record);
		
		
		
		LoginAction loginAction = new LoginAction();
		loginAction.perform(driver, record);
		
		
		OpenLinkUpdateIPAction updateIPAction = new OpenLinkUpdateIPAction();
		updateIPAction.perform(driver, record);
		
		
		
		driver.switchToWindowWithTitle("ViewAllRegisteredEmployeesPage");
		
		
		SearchAndOpenRegisteredESICAction searchAndOpenRegisteredESICAction = new SearchAndOpenRegisteredESICAction();
		searchAndOpenRegisteredESICAction.perform(driver, record);
		

		

		


	}


	
	
}
