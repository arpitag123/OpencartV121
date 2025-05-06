package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

 
public class TC001_AccountRegistrationPage extends BaseClass{
	
	
	@Test(groups={"Regression", "Master"})
	public void verify_AccountRegistration()
	{
		
		logger.info("*****Starting TC001_AccountRegistrationPage***** ");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my account link ");
		
		hp.clickRegister();
		logger.info("Clicked on my register link ");

		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing customer details ..... ");

		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com"); //randomly generated email
		regpage.setTelephone("9887706650");
		
		String password = randomAlphaNumeric();
		regpage.setPassword1(password);
		regpage.setConfirmPassword1(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected msg ");

		String confrmmsg = regpage.getConfirmationMsg();
		if(confrmmsg.equals("Your Account Has Been Created!")) 
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test failed ....");
			logger.debug("debug failed...");
			Assert.assertTrue(false);
			
		}
		
		//Assert.assertEquals(confrmmsg, "Your Account Has Been Created!");
		}
		
		catch(Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("***Finished TC001_AccountRegistrationPage Test****** ");
		
	}
	
}
