package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	// Elements
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgHeading; // MyAccount Page Heading 
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnkLogout;
	
	// Action Methods
	
	public boolean isMyAccountPageExists()  
	{
		try 
		{
			return(msgHeading.isDisplayed());
		}
		catch(Exception e) 
		{
			return(false);
		}
	}
	
	public void clickLogout() throws InterruptedException 
	{	
		//Thread.sleep(7000);
		//lnkLogout.click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", lnkLogout);
	}

}
