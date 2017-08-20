package utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public static XSSFWorkbook workBook;
	
	public static XSSFSheet testSheet;
	
	private static File file;
	
	public static XSSFSheet ReadExcelSheet(String sheetName) throws IOException
	{
		file = new File("src/main/java/excel/TestCaseEngine.xlsx");
		
		FileInputStream fis = new FileInputStream(file);
		
		workBook = new XSSFWorkbook(fis);
		
		return workBook.getSheet(sheetName);
	}
	
	public static String getCellData(XSSFSheet sheet, int rownum, int cellnum)
	{
		
		return sheet.getRow(rownum).getCell(cellnum).toString();	
		
	}
	
	public static int getRowCount(XSSFSheet sheet)
	{
		return sheet.getLastRowNum();
	}

	public static void updateTestResult(String sheet1, int rownum, String testcaseId, String testResult)
	{		
		try
		{
			file = new File("src/main/java/excel/TestCaseEngine.xlsx");
			
			FileInputStream fis = new FileInputStream(file);
			
			workBook = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = workBook.getSheet(sheet1);
			
			Row row = sheet.createRow(rownum);
			
			row.createCell(0).setCellValue(testcaseId);
			
			row.createCell(1).setCellValue(testResult);
		   
		    FileOutputStream fileOut = new FileOutputStream(file);
		    
		    workBook.write(fileOut);
		    
		    fileOut.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
}
