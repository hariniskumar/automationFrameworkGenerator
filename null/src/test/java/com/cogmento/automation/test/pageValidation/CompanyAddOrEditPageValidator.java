package com.cogmento.automation.test.pageValidation;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cogmento.automation.page.CompanyAddOrEditPage;
import ${basePackageName}.tool.BrowserUtil;

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
			
			
			
			
			
			
			
			
						
					//Element:save   Type:def
							//[save,def,,xpath,//button[@class='ui linkedin button']] Type:def
				locator = companyAddOrEditPage.getSaveLocator();
				element = companyAddOrEditPage.getSave();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:name   Type:txt
			
			
							locator = companyAddOrEditPage.getNameLocator();
				element = companyAddOrEditPage.getName();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:access   Type:def
							//[access,def,,xpath,//button[@class='ui small fluid positive toggle button']] Type:def
				locator = companyAddOrEditPage.getAccessLocator();
				element = companyAddOrEditPage.getAccess();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:usersAllowed   Type:singleSelList
			
			
			
							locator = companyAddOrEditPage.getUsersAllowedLocator();
				element = companyAddOrEditPage.getUsersAllowed();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
			
			
			
			
						
					//Element:website   Type:txt
			
			
							locator = companyAddOrEditPage.getWebsiteLocator();
				element = companyAddOrEditPage.getWebsite();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:fetch   Type:def
							//[fetch,def,,xpath,//button[contains(text(),'Fetch')]] Type:def
				locator = companyAddOrEditPage.getFetchLocator();
				element = companyAddOrEditPage.getFetch();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
			
			
						
					//Element:address   Type:stack
			
			
			
			
							locator = companyAddOrEditPage.getAddressLocator();
				element = companyAddOrEditPage.getAddress();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);			
			
			
			
			
						
					//Element:streetAddress   Type:txtInStack
			
			
			
			
			
			
			
			
						
					//Element:city   Type:txtInStack
			
			
			
			
			
			
			
			
						
					//Element:state   Type:txtInStack
			
			
			
			
			
			
			
			
						
					//Element:postcode   Type:txtInStack
			
			
			
			
			
			
			
			
						
					//Element:country   Type:singleSelListInStack
			
			
			
			
			
			
							locator = companyAddOrEditPage.getCountryLocator();
				element = companyAddOrEditPage.getCountry(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
			
						
					//Element:addressDelete   Type:defInStack
			
			
			
			
			
			
			
							locator = companyAddOrEditPage.getAddressDeleteLocator();
				element = companyAddOrEditPage.getAddressDelete(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
						
					//Element:addressAdd   Type:defInStack
			
			
			
			
			
			
			
							locator = companyAddOrEditPage.getAddressAddLocator();
				element = companyAddOrEditPage.getAddressAdd(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
						
					//Element:phone   Type:stack
			
			
			
			
							locator = companyAddOrEditPage.getPhoneLocator();
				element = companyAddOrEditPage.getPhone();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);			
			
			
			
			
						
					//Element:phoneCountry   Type:singleSelListInStack
			
			
			
			
			
			
							locator = companyAddOrEditPage.getPhoneCountryLocator();
				element = companyAddOrEditPage.getPhoneCountry(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
			
						
					//Element:phoneNumber   Type:txtInStack
			
			
			
			
			
			
			
			
						
					//Element:phoneType   Type:txtInStack
			
			
			
			
			
			
			
			
						
					//Element:phoneDelete   Type:defInStack
			
			
			
			
			
			
			
							locator = companyAddOrEditPage.getPhoneDeleteLocator();
				element = companyAddOrEditPage.getPhoneDelete(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
						
					//Element:phoneAdd   Type:defInStack
			
			
			
			
			
			
			
							locator = companyAddOrEditPage.getPhoneAddLocator();
				element = companyAddOrEditPage.getPhoneAdd(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
						
					//Element:tags   Type:txt
			
			
							locator = companyAddOrEditPage.getTagsLocator();
				element = companyAddOrEditPage.getTags();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:description   Type:txt
			
			
							locator = companyAddOrEditPage.getDescriptionLocator();
				element = companyAddOrEditPage.getDescription();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:socialChannels   Type:stack
			
			
			
			
							locator = companyAddOrEditPage.getSocialChannelsLocator();
				element = companyAddOrEditPage.getSocialChannels();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);			
			
			
			
			
						
					//Element:socialChannelName   Type:singleSelListInStack
			
			
			
			
			
			
							locator = companyAddOrEditPage.getSocialChannelNameLocator();
				element = companyAddOrEditPage.getSocialChannelName(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
			
						
					//Element:socialChannelValue   Type:txtInStack
			
			
			
			
			
			
			
			
						
					//Element:socialChannelDelete   Type:defInStack
			
			
			
			
			
			
			
							locator = companyAddOrEditPage.getSocialChannelDeleteLocator();
				element = companyAddOrEditPage.getSocialChannelDelete(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
						
					//Element:socialChannelAdd   Type:defInStack
			
			
			
			
			
			
			
							locator = companyAddOrEditPage.getSocialChannelAddLocator();
				element = companyAddOrEditPage.getSocialChannelAdd(0);
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
						
					//Element:industry   Type:txt
			
			
							locator = companyAddOrEditPage.getIndustryLocator();
				element = companyAddOrEditPage.getIndustry();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:numberOfEmployees   Type:txt
			
			
							locator = companyAddOrEditPage.getNumberOfEmployeesLocator();
				element = companyAddOrEditPage.getNumberOfEmployees();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:stockSymbol   Type:txt
			
			
							locator = companyAddOrEditPage.getStockSymbolLocator();
				element = companyAddOrEditPage.getStockSymbol();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:annualRevenue   Type:txt
			
			
							locator = companyAddOrEditPage.getAnnualRevenueLocator();
				element = companyAddOrEditPage.getAnnualRevenue();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:priority   Type:singleSelList
			
			
			
							locator = companyAddOrEditPage.getPriorityLocator();
				element = companyAddOrEditPage.getPriority();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
			
			
			
			
						
					//Element:status   Type:singleSelList
			
			
			
							locator = companyAddOrEditPage.getStatusLocator();
				element = companyAddOrEditPage.getStatus();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
			
			
			
			
						
					//Element:source   Type:singleSelList
			
			
			
							locator = companyAddOrEditPage.getSourceLocator();
				element = companyAddOrEditPage.getSource();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
			
			
			
			
						
					//Element:category   Type:singleSelList
			
			
			
							locator = companyAddOrEditPage.getCategoryLocator();
				element = companyAddOrEditPage.getCategory();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);				
			
			
			
			
			
						
					//Element:vatNumber   Type:txt
			
			
							locator = companyAddOrEditPage.getVatNumberLocator();
				element = companyAddOrEditPage.getVatNumber();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:identifier   Type:txt
			
			
							locator = companyAddOrEditPage.getIdentifierLocator();
				element = companyAddOrEditPage.getIdentifier();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
			
			
			
			
			
			
						
					//Element:logo   Type:txtFileUpload
			
			
			
			
			
			
			
			
							locator = companyAddOrEditPage.getLogoLocator();
				element = companyAddOrEditPage.getLogo();
				isElementPDE = companyAddOrEditPage.isElementPresentDisplayedAndEnabled(locator, element);
				assertTrue(isElementPDE);
						
			}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		//BrowserUtil.sleep(2000);
		driver.quit();
	}
}
