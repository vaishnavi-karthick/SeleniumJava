package Home.learning.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	private XSSFWorkbook workbook;
	public XSSFWorkbook getExcel() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\\\Users\\\\admins\\\\eclipse-workspace\\\\SeleniumJava\\\\src\\\\main\\\\java\\\\Home\\\\learning\\\\resources\\Data.xlsx");
		
		workbook = new XSSFWorkbook(fis);
		
		return workbook;
	}
	public HashMap<String, String> getDataFromExcel() throws IOException {
		
		workbook = getExcel();
		HashMap<String, String> loginTests = new HashMap<String, String>();

		XSSFSheet sheetOne = workbook.getSheet("Testcases");
		int numOfRows = sheetOne.getPhysicalNumberOfRows();
		//Iterator<Row> rowIterator = sheetOne.iterator();
		for(int i=1; i<numOfRows; i++)
		{
			Row row = sheetOne.getRow(i);
			Iterator<Cell> cellIterator = row.iterator();
			while(cellIterator.hasNext()) {
				
				Cell userName = cellIterator.next();
				Cell password = cellIterator.next();
				System.out.println("cell : "+userName +"-"+ password);
				loginTests.put(userName.getStringCellValue(), password.getStringCellValue());
			}
		}
		return loginTests;
		
	}
	
	public HashMap<String, Integer> getItemList() throws IOException {
		workbook = getExcel();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		XSSFSheet sheetOne = workbook.getSheet("Items");
		int numOfRows = sheetOne.getPhysicalNumberOfRows();
		for(int i=1; i<numOfRows; i++) {
			Row row = sheetOne.getRow(i);
			Iterator<Cell> cellIterator = row.iterator();
			while(cellIterator.hasNext()) {
				Cell itemName = cellIterator.next();
				Cell itemCount = cellIterator.next();
				System.out.println("item : "+itemName+" count : "+itemCount);
				items.put(itemName.getStringCellValue(), (int) itemCount.getNumericCellValue());
			}
		}
		return items;
	}
}
