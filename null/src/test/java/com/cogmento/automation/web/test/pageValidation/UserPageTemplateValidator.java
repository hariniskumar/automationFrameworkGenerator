package com.cogmento.automation.web.test.pageValidation;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cogmento.automation.web.page.UserPageTemplate;
import com.cogmento.automation.web.tool.BrowserUtil;

public class UserPageTemplateValidator{

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
	public void UserPageTemplateValidationTest() {
				
		UserPageTemplate userPageTemplate = new UserPageTemplate(driver);
		By locator;
		WebElement element;
		boolean isElementPDE;
		
					//Element:home   Type:def
							//[home,def,,xpath,//span[contains(text(),'Home')]] Type:def
				locator = userPageTemplate.getHomeLocator();
				element = userPageTemplate.getHome();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
			}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		//BrowserUtil.sleep(2000);
		driver.quit();
	}
}
