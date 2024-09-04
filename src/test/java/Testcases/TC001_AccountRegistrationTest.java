package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import PageObjects.HomePage;
import PageObjects.RegistrationPage;

public class TC001_AccountRegistrationTest extends BaseClass {
	RegistrationPage rg;
	@Test(groups={"Regression","Master"})
	public void verify_Account_registration() {
		logger.info("****TC001_AccountRegistrationTest ***");
		try
		{
	 HomePage homepage =new HomePage(driver);
		homepage.ClickMyAccount();
		logger.info("****Clicked My Account Link ***");
		homepage.ClickOnRegister();
		logger.info("**** clicked on register ...***");
		rg = new RegistrationPage (driver);
		
		logger.info("****providing customer Details ***");
		rg.setfristname(RandomString().toUpperCase());
		rg.setLastName(RandomString().toUpperCase());
		rg.setEmail(RandomString()+"@gmail.com");
		rg.setTelephoneNumber(RandomNumber());
		String password = RandomAlphaNumber();
		rg.setpassword(password);
		rg.setConfirmpassword(password);
		rg.ClickOnSubscribe();
		rg.ClickOnprivacy();
		rg.CilckOnContinue();
		
		logger.info("*** validating ecpected message *****");
		String cofmsg=rg.getConforimMsg();
		Assert.assertEquals(cofmsg,"Your Account Has Been Created!" );
	 
	}
	catch (Exception e) {
		logger.error("Test Failed ");
		//logger.debug(" Debug Logs ");
		Assert.fail();
	}
logger.info("Test case Finished ");
		
}
}
