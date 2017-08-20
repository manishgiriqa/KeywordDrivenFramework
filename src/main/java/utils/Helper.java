package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import keywords.KeywordActions;

public class Helper {
	
	public static WebElement getElement(String element)
	{
		return KeywordActions.driver.findElement(By.xpath(element));
	}

}
