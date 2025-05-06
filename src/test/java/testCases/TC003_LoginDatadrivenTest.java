package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public final class TC003_LoginDatadrivenTest extends BaseClass
{

	@Test(dataProvider = "LoginData" , dataProviderClass = DataProviders.class , groups="datadriven") // getting data provider from diff class and package 
	public void verify_loginDDT(String email, String pwd, String exp) 
	{
		logger.info("** Starting TC003_LoginDatadrivenTest ************");

		try 
		{
		//Homepage
	     HomePage hp = new HomePage(driver);
		 hp.clickMyAccount();
		 hp.clickLogin();
				
		//loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
	    lp.clickLogin();

	  //myacount page
	  	MyAccountPage macc = new MyAccountPage(driver);
	  	boolean targetPage = macc.isMyAccountPageExists();

	  	
 /*
 *data is valid - login is success - test passed  -logout 
 *data is valid - login failed - test failed
 *data is invalid - login success - test fail - logout
 *data is invalid - login fail - test pass  
 */
	  	 
	  	if(exp.equalsIgnoreCase("valid")) 
	  	{
	  		if(targetPage==true) // login is success
	  		{
	  			macc.clickLogout();
	  			Assert.assertTrue(true); // test is pass
	  		}
	  		else  //if invalid data 
	  		{
	  			Assert.assertTrue(false); // test is fail 
	  		}
	  	}
	  	if(exp.equalsIgnoreCase("invalid"))
	  	 {
	  		if(targetPage==true) // login is success
	  		{
	  			macc.clickLogout();
	  			Assert.assertTrue(false); // test is fail
	  		}
	  		else 
	  		{
	  			Assert.assertTrue(true); // test is pass

	  		}
	  		
	  	 }
		}
	  	catch(Exception e) 
	  	{
	  		
	  		Assert.fail();
	  	}
	  	
		logger.info("*** finished TC003_LoginDatadrivenTest*******");

	  	  	  	
	}
	
}
