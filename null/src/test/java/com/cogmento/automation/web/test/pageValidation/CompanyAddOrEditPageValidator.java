package com.cogmento.automation.web.test.pageValidation;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cogmento.automation.web.page.CompanyAddOrEditPage;
import com.cogmento.automation.web.tool.BrowserUtil;

public class CompanyAddOrEditPageValidator{

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
	public void CompanyAddOrEditPageValidationTest() {
				
		CompanyAddOrEditPage companyAddOrEditPage = new CompanyAddOrEditPage(driver);
		By locator;
		WebElement element;
		boolean isElementPDE;
		
					//Element:cancel   Type:def
							//[cancel,def,,xpath,//button[contains(text(),'Cancel')]] Type:def
				locator = companyAddOrEditPage.getCancelLocator();
				element = companyAddOrEditPage.getCancel();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
			}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		//BrowserUtil.sleep(2000);
		driver.quit();
	}
}
