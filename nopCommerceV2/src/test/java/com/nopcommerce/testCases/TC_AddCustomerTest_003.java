package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.utilities.XLUtils;

public class TC_AddCustomerTest_003 extends BaseClass
{

	@Test(dataProvider="Createcustomer")
	public void addNewCustomer(String username,String password,String pwd1,String CustomerRoles,String ManagerOfVendor,String Gender,String FirstName,String LastName,String Dob,String CompanyName,String AdminContent,String Exmessage ) throws IOException, InterruptedException
	{
		driver.get(baseURL);
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		
		lp.setPassword(password);
		logger.info("password is provided");
		
		lp.clickLogin();
		
		logger.info("providing customer details....");
		
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		
		addcust.clickOnAddnew();
		
		String email = randomestring() + "@gmail.com";
		addcust.setEmail(email);
				
		addcust.setPassword(pwd1);
		
		//Registered - default
		//The customer cannot be in both 'Guests' and 'Registered' customer roles
		//Add the customer to 'Guests' or 'Registered' customer role
		addcust.setCustomerRoles(CustomerRoles);
		
		addcust.setManagerOfVendor(ManagerOfVendor);
		
		addcust.setGender(Gender);
		
		addcust.setFirstName(FirstName);
		addcust.setLastName(LastName);
		
		addcust.setDob(Dob); // Format: D/MM/YYY
		
		addcust.setCompanyName(CompanyName);
		addcust.setAdminContent(AdminContent);
	
		addcust.clickOnSave();
		
		logger.info("validation started....");
				
		//String msg=driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']")).getText();
		
		String msg = driver.findElement(By.tagName("body")).getText();
			
		
		if(msg.contains(Exmessage))
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");
		}
		else
		{
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
				
	}
	
	@DataProvider(name="Createcustomer")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/nopcommerce/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet2");
		int colcount=XLUtils.getCellCount(path,"Sheet2",1);
		
		String createcustomerdata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				createcustomerdata[i-1][j]=XLUtils.getCellData(path,"Sheet2", i,j);
			}
		}
		
		return createcustomerdata;
		
	}
	
}
