package com.cleartrip.base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cleartrip.util.PropertyFileReader;

public class CleartripBase {
	public Logger log = Logger.getLogger(CleartripBase.class);
	public WebDriver driver;
	public static Properties prop=null;
	
	public int PAGE_LOAD_TIMEOUT =50;
	public int IMPLICIT_WAIT =20;

	public CleartripBase(){
		PropertyFileReader.loadProperty();
	}
	public void setup() {
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Enter a valid browser name");
		}

		log.info(browser+" browser is opened");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		log.info("Url is intiated");
	}

}
