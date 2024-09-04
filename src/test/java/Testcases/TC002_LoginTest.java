package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {
	@Test(groups={"Sanity","Master"})
	public void verify_Login() {
		logger.info("***** Starting Tc_002 Login Test ****");
		
		
		try {
		//Homepage
		HomePage hp = new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		//loginpage
		LoginPage lp = new LoginPage(driver);
		// here we have to pass vaild credentials
		lp.setEmailAddress(p.getProperty("email"));
		lp.setLoginPassword(p.getProperty("password"));
		lp.ClickLogin();
		
		//MyAccountPage
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean tagetpage = macc.isMyAccountPageExists();
		//Assert.assertTrue(tagetpage);
		Assert.assertEquals(tagetpage, true," Login Failed ");
	
	}catch(Exception e) {
		Assert.fail();
	}
		logger.info("***** Finished Tc_002_ LoginTest*******");

}
}
