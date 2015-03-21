package com.esic.Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXFileReader {

	public static void main(String args[]) throws IOException {

		// C:\\Users\\meet\\git\\AutoESIC\\src\\main\\resources\\samplefile\\SampleFile.xlsx

		String fileName = "C:\\Users\\meet\\git\\AutoESIC\\src\\main\\resources\\samplefile\\SampleFile.xlsx";
		String sheetName = "final";
		
		
		XLSXFileReader  reader = new XLSXFileReader();
		
		XSSFSheet mySheet = reader.readSheetInExcel(fileName, sheetName);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t");
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t");
					break;
				default:

				}
			}
			System.out.println("");
		}
	}

	
	/**
	 * 
	 * read sheet by absolute file name and sheet name..
	 * @param fileName
	 * @param sheetName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private  XSSFSheet readSheetInExcel(String fileName, String sheetName)
			throws FileNotFoundException, IOException {
		File myFile = new File(fileName);
		FileInputStream fis = new FileInputStream(myFile);

		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
		return mySheet;
	}
}