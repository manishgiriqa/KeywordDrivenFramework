package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	private static Properties prop;
	
	public static String getPropertyValue(String property)
	{		
		prop = new Properties();
		
		try 
		{			
			prop.load(new FileReader("src/main/java/config/application.properties"));			
		} 		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
		}
		
		return prop.getProperty(property);
	}
}
