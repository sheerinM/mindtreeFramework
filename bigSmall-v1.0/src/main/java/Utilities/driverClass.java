package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driverClass {

	public static WebDriver driver;
	public Properties prop= new Properties();
	public WebDriver initializeDriver() throws IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\Sheerin Mukherjee\\eclipse-mindtree\\bigSmall-v1.0\\src\\main\\resources\\Resources\\config.properties");
	    prop.load(fis);
	    String browserName= prop.getProperty("browser");
	    if(browserName.equalsIgnoreCase("Chrome"))
	    {
	    	System.setProperty("webdriver.chrome.driver","C:\\Users\\Sheerin Mukherjee\\Desktop\\Selenium Drivers\\chromedriver\\chromedriver.exe" );
			driver= new ChromeDriver();
	    }
	    else
	    {
	    	System.setProperty("webdriver.gecko.driver","C:\\Users\\Sheerin Mukherjee\\Desktop\\Selenium Drivers\\gecko driver\\geckodriver.exe" );
			driver= new FirefoxDriver();
	    }
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	    driver.manage().deleteAllCookies();
		return driver;
	}
	
	public void getScreenshot(String testName) throws IOException
	{
	    TakesScreenshot ts=((TakesScreenshot)driver);
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg= new File(".\\screenshots\\"+ testName+".png");
		FileUtils.copyFile(src, trg);
	}
}

