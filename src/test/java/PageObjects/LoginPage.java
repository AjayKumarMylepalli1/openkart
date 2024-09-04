package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_EmailAddress;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btn_login;
	
	public void setEmailAddress(String email) {
		txt_EmailAddress.sendKeys(email);
	}
	public void setLoginPassword(String pwd) {
		txt_Password.sendKeys(pwd);
	}
	public void ClickLogin() {
		btn_login.click();
	}

}
