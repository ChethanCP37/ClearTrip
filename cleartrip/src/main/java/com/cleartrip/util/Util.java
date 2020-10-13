package com.cleartrip.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


public class Util {
	public static int TIMEOUT_SEC=30;
	public static WebDriverWait wait=null;
	public static boolean val;

	public static void waitForElement(WebDriver driver,WebElement element) {
		wait = new WebDriverWait(driver,TIMEOUT_SEC);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static boolean dateValidator(String fromDate,  String toDate) throws ParseException {
		Date depDate= new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
		Date yesDate = new Date(depDate.getTime() - 2);
		Date retDate= new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
		Reporter.log("Depart Date is --> "+depDate+", Return date is --> "+retDate+", Yesterday's date is "+yesDate,true);
		if((retDate.after(depDate)) && (depDate.after(yesDate))) {
			val=true;	
		}
		else {
			Assert.fail("Entered date is not correct, please enter a valid depart and return dates; RetrunDate:"+retDate+", DepartDate:"+depDate);	
		}
		return val;
	}
	public static void cityCompare(String fromCity, String toCity) {
		if(fromCity.equalsIgnoreCase(toCity)) {
			Assert.fail("Depart and return cities shouldn't be same please select different cities");
		}
		if((fromCity.isEmpty() && toCity.isEmpty())){
			Assert.fail("City name should not be empty");
		}

	}
	
	public static void departReturnCities(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)");
	}

}
