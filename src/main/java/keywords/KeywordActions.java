package keywords;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Helper;
import utils.PropertyReader;


public class KeywordActions {
	
	public static WebDriver driver;	
	
	public static void openBrowser(String browser)
	{
		DesiredCapabilities caps = new DesiredCapabilities();	
		
		switch(browser)
		{
			case " firefox":
				
				System.setProperty("webdriver.gecko.driver", PropertyReader.getPropertyValue(browser));
				
				caps.setCapability("marionette", true);
				
				driver = new FirefoxDriver(caps);	
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				driver.manage().window().maximize();
			
			break;
			
			case "chrome" :
				
				System.setProperty("webdriver.chrome.driver",PropertyReader.getPropertyValue(browser));
				
				driver = new ChromeDriver();
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				driver.manage().window().maximize();
			
			break;
		}
	}
	
	public static void navigate(String object)
	{
		driver.get(PropertyReader.getPropertyValue(object));
	}
	
	public static boolean verifyPageTitle(String pageTitle)
	{		
		//Assert.assertTrue(driver.getTitle().equals(pageTitle),"Page tile has changed");
		
	//	Assert.assertTrue("Page title is changed", driver.getTitle().equals(pageTitle));
		
		if(driver.getTitle().equals(pageTitle))
			return true;
		else
			return false;
	}
	
	public static void waitForElement(WebElement element, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		
		wait.until(ExpectedConditions.visibilityOf(element));	
		
	}
	
	public static void inputData(String object, String value)
	{
		WebElement element = Helper.getElement(PropertyReader.getPropertyValue(object));
		
		waitForElement(element, 30);
		
		element.clear();
		
		element.sendKeys(value);
	}
	
	public static void click(String object)
	{
		WebElement element = Helper.getElement(PropertyReader.getPropertyValue(object));
		
		waitForElement(element, 30);
		
		element.click();
	}
	
	public static void closeBrowser()
	{
		driver.quit();
	}
	
	

}
