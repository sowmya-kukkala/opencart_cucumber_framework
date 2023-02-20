package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	// Elements
	
	@FindBy(id="input-email") WebElement txtEmailAddress;
	
	@FindBy(id="input-password") WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
	
	// Action Methods
	
	public void setEmail(String email) 
	{
		txtEmailAddress.sendKeys(email);
	}
	
	public void setPassword(String pwd) 
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin() 
	{
		btnLogin.click();
	}
	

}
