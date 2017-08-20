package tests;

import keywords.KeywordActions;
import utils.ExcelUtilities;
import utils.Log;
import utils.PropertyReader;
import java.io.*;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class TestRunner {
	
	static String keyword, object, data;
	
	static String testCaseId;
	
	static String testResult;	
	
	static XSSFSheet testSuite;
	
	static XSSFSheet testResults;
	
	static XSSFSheet testcases;

	public static void main(String[] args) throws IOException {
		
		DOMConfigurator.configure("log4j.xml");
		
		testSuite = ExcelUtilities.ReadExcelSheet("TestSuite");
		
		testResults = ExcelUtilities.ReadExcelSheet("TestResult");
		
		testcases = ExcelUtilities.ReadExcelSheet(PropertyReader.getPropertyValue("testcasesheet"));
		
		int teststeps = 1;
		
		int numberOfTestCasesToRun = ExcelUtilities.getRowCount(testSuite);
		
		int currentTestCase = 1;
		
		String runningTestCase;
		
		while(currentTestCase<=numberOfTestCasesToRun)
		{
			testCaseId = ExcelUtilities.getCellData(testSuite,currentTestCase,0);
			
			if(ExcelUtilities.getCellData(testSuite,currentTestCase,1).equals("Y"))
			{
				//System.out.println("====================New Test Started==============================");
				
				//System.out.println("Running test case : " + testCaseId);
				
				Log.startTestCase(testCaseId);
				
				runningTestCase = ExcelUtilities.getCellData(testcases,teststeps, 0);
				
				while(runningTestCase.equals(testCaseId))
				{
					keyword = ExcelUtilities.getCellData(testcases,teststeps, 2);
					
					object = ExcelUtilities.getCellData(testcases,teststeps, 3);
					
					data = ExcelUtilities.getCellData(testcases,teststeps, 4);	
					
					Log.info(ExcelUtilities.getCellData(testcases,teststeps, 1));
					
					switch(keyword)
					{
					case "openBrowser":
						
						KeywordActions.openBrowser(data);
						
						break;
					case "navigate":
						
						KeywordActions.navigate(object);
						
						break;
						
					case "input":
						
						KeywordActions.inputData(object, data);
						
						break;
						
					case "click":
						
						KeywordActions.click(object);
						
						break;
						
					case "closeBrowser":
						
						KeywordActions.closeBrowser();
						
						break;
						
					case "verifyPageTitle": 				
						
							if(KeywordActions.verifyPageTitle(data))
								testResult = "Passed";
							else
								testResult = "Failed";
						break;										
					}	
					
					teststeps++;
					
					runningTestCase = ExcelUtilities.getCellData(testcases,teststeps, 0);
					
				}	
								
				ExcelUtilities.updateTestResult("TestResult", currentTestCase, testCaseId, testResult);
				
				Log.endTestCase(testCaseId);
				//System.out.println("====================Test Completed==============================");
			}			
			
			currentTestCase++;
			}
		}
		
		
}
