package com.cogmento.automation.test.pageValidation;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cogmento.automation.page.UserPageTemplate;
import ${basePackageName}.tool.BrowserUtil;

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
			
			
			
			
			
			
			
			
						
					//Element:calendar   Type:def
							//[calendar,def,,xpath,//span[contains(text(),'Calendar')]] Type:def
				locator = userPageTemplate.getCalendarLocator();
				element = userPageTemplate.getCalendar();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:calendarAdd   Type:def
							//[calendarAdd,def,calendar,xpath,//span[contains(text(),'Calendar')]/../../button/i[@class='plus inverted icon']] Type:def
				locator = userPageTemplate.getCalendarAddLocator();
				element = userPageTemplate.getCalendarAdd();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:contacts   Type:def
							//[contacts,def,,xpath,//span[contains(text(),'Contacts')]] Type:def
				locator = userPageTemplate.getContactsLocator();
				element = userPageTemplate.getContacts();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:contactAdd   Type:def
							//[contactAdd,def,contacts,xpath,//span[contains(text(),'Contacts')]/../../button/i[@class='plus inverted icon']] Type:def
				locator = userPageTemplate.getContactAddLocator();
				element = userPageTemplate.getContactAdd();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:companies   Type:def
							//[companies,def,,xpath,//span[contains(text(),'Companies')]] Type:def
				locator = userPageTemplate.getCompaniesLocator();
				element = userPageTemplate.getCompanies();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:companyAdd   Type:def
							//[companyAdd,def,companies,xpath,//span[contains(text(),'Companies')]/../../button/i[@class='plus inverted icon']] Type:def
				locator = userPageTemplate.getCompanyAddLocator();
				element = userPageTemplate.getCompanyAdd();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:deals   Type:def
							//[deals,def,,xpath,//span[contains(text(),'Deals')]] Type:def
				locator = userPageTemplate.getDealsLocator();
				element = userPageTemplate.getDeals();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:dealAdd   Type:def
							//[dealAdd,def,deals,xpath,//span[contains(text(),'Deals')]/../../button/i[@class='plus inverted icon']] Type:def
				locator = userPageTemplate.getDealAddLocator();
				element = userPageTemplate.getDealAdd();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:tasks   Type:def
							//[tasks,def,,xpath,//span[contains(text(),'Tasks')]] Type:def
				locator = userPageTemplate.getTasksLocator();
				element = userPageTemplate.getTasks();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:taskAdd   Type:def
							//[taskAdd,def,tasks,xpath,//span[contains(text(),'Tasks')]/../../button/i[@class='plus inverted icon']] Type:def
				locator = userPageTemplate.getTaskAddLocator();
				element = userPageTemplate.getTaskAdd();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:cases   Type:def
							//[cases,def,,xpath,//span[contains(text(),'Cases')]] Type:def
				locator = userPageTemplate.getCasesLocator();
				element = userPageTemplate.getCases();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:caseAdd   Type:def
							//[caseAdd,def,cases,xpath,//span[contains(text(),'Cases')]/../../button/i[@class='plus inverted icon']] Type:def
				locator = userPageTemplate.getCaseAddLocator();
				element = userPageTemplate.getCaseAdd();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:calls   Type:def
							//[calls,def,,xpath,//span[contains(text(),'Calls')]] Type:def
				locator = userPageTemplate.getCallsLocator();
				element = userPageTemplate.getCalls();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:callAdd   Type:def
							//[callAdd,def,calls,xpath,//span[contains(text(),'Calls')]/../../button/i[@class='plus inverted icon']] Type:def
				locator = userPageTemplate.getCallAddLocator();
				element = userPageTemplate.getCallAdd();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:documents   Type:def
							//[documents,def,,xpath,//span[contains(text(),'Documents')]] Type:def
				locator = userPageTemplate.getDocumentsLocator();
				element = userPageTemplate.getDocuments();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:documentAdd   Type:def
							//[documentAdd,def,documents,xpath,//span[contains(text(),'Documents')]/../../button/i[@class='plus inverted icon']] Type:def
				locator = userPageTemplate.getDocumentAddLocator();
				element = userPageTemplate.getDocumentAdd();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:email   Type:def
							//[email,def,,xpath,//span[contains(text(),'Email')]] Type:def
				locator = userPageTemplate.getEmailLocator();
				element = userPageTemplate.getEmail();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:campaigns   Type:def
							//[campaigns,def,,xpath,//span[contains(text(),'Campaigns')]] Type:def
				locator = userPageTemplate.getCampaignsLocator();
				element = userPageTemplate.getCampaigns();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:forms   Type:def
							//[forms,def,,xpath,//span[contains(text(),'Forms')]] Type:def
				locator = userPageTemplate.getFormsLocator();
				element = userPageTemplate.getForms();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:nameDisplay   Type:def
							//[nameDisplay,def,,xpath,//span[@class='user-display']] Type:def
				locator = userPageTemplate.getNameDisplayLocator();
				element = userPageTemplate.getNameDisplay();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:pinnedRecords   Type:def
							//[pinnedRecords,def,,xpath,//i[@class='pin icon']] Type:def
				locator = userPageTemplate.getPinnedRecordsLocator();
				element = userPageTemplate.getPinnedRecords();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:deleteRecords   Type:def
							//[deleteRecords,def,,xpath,//i[ @class='trash alternate outline icon']] Type:def
				locator = userPageTemplate.getDeleteRecordsLocator();
				element = userPageTemplate.getDeleteRecords();
				isElementPDE = userPageTemplate.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
			}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		//BrowserUtil.sleep(2000);
		driver.quit();
	}
}
