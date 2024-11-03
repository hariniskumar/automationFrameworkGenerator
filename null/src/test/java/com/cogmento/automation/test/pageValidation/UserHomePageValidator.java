package com.cogmento.automation.test.pageValidation;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cogmento.automation.page.UserHomePage;
import ${basePackageName}.tool.BrowserUtil;

public class UserHomePageValidator{

	WebDriver driver;

	@BeforeMethod
	@Parameters({ "browser", "baseUrl" })
	public void setup(String browser, String baseUrl) {
		System.out.println(baseUrl);
		driver = BrowserUtil.getWebDriver(browser);
		driver.get(baseUrl);
		//TODO: other necessary code before test is executed		
	}

	@Test
	public void UserHomePageValidationTest() {
				
		UserHomePage userHomePage = new UserHomePage(driver);
		By locator;
		WebElement element;
		boolean isElementPDE;
		
			}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		//BrowserUtil.sleep(2000);
		driver.quit();
	}
}
