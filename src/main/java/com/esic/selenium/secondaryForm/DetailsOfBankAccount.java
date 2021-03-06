package com.esic.selenium.secondaryForm;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.PageObject;
import com.esic.selenium.homePage.SubmitFormAndExportErrors;
import com.esic.selenium.prelogin.Launch;
import com.esic.util.DropdownUtil;

/**
 * 
 * @author Mauli
 *
 *user story 28
 *15. Details of Bank Accounts of Insured Person: 
 *
 *not mandatory
 */
public class DetailsOfBankAccount  implements PageObject{
	
	final static Logger logger = Logger.getLogger(DetailsOfBankAccount.class);

	@FindBy(xpath="//td[contains(.,'Details of Bank Accounts of Insured Person:')]/following-sibling::td/a")
	WebElement detailsOfBankAccountLink;
	
	@FindBy(id="ctl00_HomePageContent_gdvBankDetails_ctl03_AccountNumber")
	WebElement accountNum;
	
	@FindBy(id="ctl00_HomePageContent_gdvBankDetails_ctl03_TypeOfAccount")
	WebElement accountType;
	
	@FindBy(id="ctl00_HomePageContent_gdvBankDetails_ctl03_BankName")
	WebElement nameOfBank;
	
	@FindBy(id="ctl00_HomePageContent_gdvBankDetails_ctl03_BranchName")
	WebElement nameOfBranch;
	
	//not mandatory
	@FindBy(id="ctl00_HomePageContent_gdvBankDetails_ctl03_MICRCode")
	WebElement MICRCode;
	
	//not mandatory 
	@FindBy(id="ctl00_HomePageContent_gdvBankDetails_ctl03_IFSCCode")
	WebElement IFSCCode;
	
	@FindBy(id="ctl00_HomePageContent_ctrlButtonSave")
	WebElement save;
	
	@FindBy(id="ctl00_HomePageContent_close_btn")
	WebElement close;
	
	public SubmitFormAndExportErrors process(ESICRecord record){
		detailsOfBankAccountLink.click();
		Launch.switchToNewWindow();
		
		enterDetail(record.getBankAccountNo(),accountNum);
		selectTypeOfAccount(record.getBankAccountType());
		enterDetail(record.getBankAccountBankName(), nameOfBank);
		enterDetail(record.getBankAccountBranchName(), nameOfBranch);
		enterDetail(record.getBankAccountMICR(), MICRCode);
		enterDetail(record.getBankAccountIFSC(), IFSCCode);
		
		save.click();
		close.click();
		Launch.switchToNewWindow();
		return new SubmitFormAndExportErrors();
	}
	
	protected void enterDetail(String value,WebElement element){
		element.clear();
		element.sendKeys(value.trim());
		logger.info("Value entered is "+value);
	}
	
	private void selectTypeOfAccount(String typeValue){
		accountType.click();
		DropdownUtil.selectDropdown(typeValue, accountType);
	}
	
}
