package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement fristname; 
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement emailId;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telephonenumber;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement confirmpassword;
	
	@FindBy(xpath="//label[normalize-space()='Yes']//input[@name='newsletter']")
	WebElement btn_subscribe;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btn_privacy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement clickContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg;
	
	public void setfristname(String fname) {
		fristname.sendKeys(fname);
	}
	public void setLastName(String lname) {
		lastname.sendKeys(lname);
	}
	public void setEmail(String email) {
		emailId.sendKeys(email);
	}
	public void setTelephoneNumber(String number) {
		telephonenumber.sendKeys(number);
	}
	public void setpassword(String pwd) {
		password.sendKeys(pwd);
	}
	public void setConfirmpassword(String pwd) {
		confirmpassword.sendKeys(pwd);
	}
	public void ClickOnSubscribe() {
		btn_subscribe.click();
	}
	public void  ClickOnprivacy() {
		btn_privacy.click();
	}
	public void CilckOnContinue() {
		clickContinue.click();
	}
	public String getConforimMsg() {
		try {
		return (msg.getText());
		}
		catch(Exception e) {
			return (e.getMessage());
		}
	}
	
}
