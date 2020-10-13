package com.cleartrip.page;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cleartrip.base.CleartripBase;
import com.cleartrip.util.Util;

public class SearchFlightsResult extends CleartripBase {
	public Actions action = new Actions(driver);
	
	@FindBy(xpath="(//button[text()='Book'])[2]")
	public WebElement bookFlightButton;
	
	@FindBy(xpath="(//div[@class=\"rt-tuple-container__details ms-grid-row-2\"])[1]")
	public WebElement departflightResults;
	
	@FindBy(xpath="(//div[@data-test-attrib=\"return-view\"]//div[@class=\"flex flex-middle mb-3 ms-grid-column-1\"])[1]")
	public WebElement returnflightResults;
	
	@FindBy(xpath="//a[text()='Flights']")
	public WebElement flightOption;
	
	@FindBy(xpath="//span[text()='Details']")
	public WebElement DetailsButton;
	
	
	public SearchFlightsResult(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void selectDepartAndReturnFlights() {
		Util.waitForElement(driver, departflightResults);
		Util.departReturnCities(driver);
		departflightResults.click();
		returnflightResults.click();
		
		String parent=driver.getWindowHandle();
		System.out.println("parent window address "+parent);
		DetailsButton.click();
		bookFlightButton.click();
		Set<String> allWindowHandles = driver.getWindowHandles();
		
		for(String winHandle:allWindowHandles) {
			System.out.println("Child windows "+winHandle);
			driver.switchTo().window(winHandle);
		}
	}
}
