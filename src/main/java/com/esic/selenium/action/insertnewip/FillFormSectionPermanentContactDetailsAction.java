package com.esic.selenium.action.insertnewip;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.esic.domain.ESICRecord;
import com.esic.selenium.action.Action;
import com.esic.selenium.action.contact.ContactDetailsAbstractAction;
import com.esic.selenium.pom.insertnewip.PermanentContactDetailsPOM;

public class FillFormSectionPermanentContactDetailsAction extends ContactDetailsAbstractAction implements Action  {

	final static Logger logger = Logger.getLogger(FillFormSectionPermanentContactDetailsAction.class);

	private PermanentContactDetailsPOM form;
	private WebDriver driver;
	//need to make it sync as we are using class variable form.
	public synchronized void perform(WebDriver driver, ESICRecord record) {

		form = PageFactory.initElements(driver, PermanentContactDetailsPOM.class);
		this.driver = driver;

		//check if box is ticked, if not then enter details below
				if(record.isCheckBoxTrueForPermanentDetails()){
					form.copyPresentAddressDetails.click();
				}
				else{
				enterMandatoryAddress(record.getPresentAddress_Address());
				enterDetailForField("email", record.getPermanntAddress_emailID());
				enterDetailForField("district", record.getPermanntAddress_District());
				enterDetailForField("state", record.getPermanntAddress_State());
				enterDetailForField("district", record.getPermanntAddress_District());
				enterDetailForField("phone", record.getPermanntAddress_PhoneNo());
				enterDetailForField("mobile", record.getPermanntAddress_MobileNo());
				enterDetailForField("pincode", record.getPermanntAddress_PinCode());
				}

	}
	
	
	protected void loadAddressValues() {
		mandatoryAddress = new ArrayList<WebElement>();
		mandatoryAddress.add(form.mandatoryAddressLine1);
		mandatoryAddress.add(form.mandatoryAddressLine2);
		mandatoryAddress.add(form.mandatoryAddressLine3);
	}
	
	protected void loadDistrict() {
		// dynamically loaded after state is selected and hence has to called
		// after state is selected
		form = PageFactory.initElements(driver, PermanentContactDetailsPOM.class);
	}
	
	public void enterDetailForField(String fieldName,String fieldValue){
		if(fieldName.equals("state")) selectState(fieldValue, form.state);
		else if(fieldName.equals("district")) selectDistrict(fieldValue, form.district);
		else if(fieldName.equals("email")) enterDetail(fieldValue, form.email);
		else if(fieldName.equals("mobile")) enterDetail(fieldValue, form.mobileNo);
		else if(fieldName.equals("pincode")) enterDetail(fieldValue, form.pincode);
		else if(fieldName.equals("stdcode")) enterDetail(fieldValue, form.phoneSTDCode);
		else if(fieldName.equals("phone")) enterDetail(fieldValue, form.phoneNo);
		else logger.error("Incorrect fieldName");
	}
	

}
