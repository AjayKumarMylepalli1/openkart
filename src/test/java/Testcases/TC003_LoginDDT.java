package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import Utilites.DataProviders;


/*
 * Data is vaild - login  success - test pass - logout
 *				- Login failed - test fail
 * 
 * Data is invaild - login success - test fail - log out
 * 					- login faild - test pass 
 */
public class TC003_LoginDDT  extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass = DataProviders.class,groups="Datadriven") // getting data provider from different
	public void verify_loginDDT(String email,String pwd,String exp) {
		logger.info("**** Starting Tc_003 LoginDDT****");
		try {
				//Homepage
			HomePage hp = new HomePage(driver);
			hp.ClickMyAccount();
			hp.ClickLogin();
			
			//loginpage
			LoginPage lp = new LoginPage(driver);
			// here we have to pass vaild credentials
			lp.setEmailAddress(email);
			lp.setLoginPassword(pwd);
			lp.ClickLogin();
			
			//MyAccountPage
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean tagetpage = macc.isMyAccountPageExists();

			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(tagetpage ==true)
				{
					Assert.assertTrue(true);
					macc.clickLogout();
				}
				else
				{
				Assert.assertTrue(false);	
				}
			}
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(tagetpage ==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("**********Finished TC003_LOGinDDT***********");
	}
	
}
