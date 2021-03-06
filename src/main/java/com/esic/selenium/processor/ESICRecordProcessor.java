package com.esic.selenium.processor;

import org.apache.log4j.Logger;

import com.esic.domain.ESICRecord;
import com.esic.selenium.prelogin.Launch;
import com.esic.selenium.processor.insertnewip.InsertESICRecordProcessor;
import com.esic.selenium.processor.update.ESICRecordUpdater;

public class ESICRecordProcessor extends ESICRecordProcessorBase {

	final static Logger logger = Logger.getLogger(ESICRecordProcessor.class);

	
	private Launch launchInstance;

	private ESICRecordUpdater updater = new ESICRecordUpdater();
	
	private InsertESICRecordProcessor insertProcessor = new InsertESICRecordProcessor();
	

	/**
	 * main method to process a single record
	 * It will not update {@link ESICRecord#autoEsicComments} and {@link ESICRecord#autoEsicStatus} columns.
	 * As soon as this function ends it will get updated in excel file..
	 * @param record
	 * @throws Exception 
	 */
	public void processRecord(ESICRecord record) {
		logger.trace(record);
		
		
		if(launchInstance == null)
		{
			launchInstance = new Launch();
		}
		
		
		if(record.getESICNo() != null && !record.getESICNo().isEmpty())
		{
			//update flow
			updater.processRecord(record);
		}
		else
		{
			//add flow..
			//launchInstance.process(record);
			insertProcessor.processRecord(record);
			logger.info("Comments are: "+record.getAutoEsicComments());
			record.setAutoEsicComments(record.getAutoEsicComments().trim());
			if(record.getAutoEsicComments()!=null && !record.getAutoEsicComments().equals("")){
				record.setAutoEsicStatus("FAILTEST");
			}
			else{
			record.setAutoEsicStatus("PASSTEST");
			}
		}
	}



}
