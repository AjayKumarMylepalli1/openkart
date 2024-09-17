package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//h2[normalize-space()='My Account']")// home page heading
	WebElement msgHeading;
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']")//added in step 6
	WebElement lnklogout;
	public boolean isMyAccountPageExists() {
		try {
		return (msgHeading.isDisplayed());
		}
		catch(Exception e) 
		{
			System.out.println("this is captured ");
		}
		return false;
	}
	public void clickLogout() {
		lnklogout.click();
	}
}
