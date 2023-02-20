package stepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;
import io.cucumber.java.en.Then;

public class steps {
	
	WebDriver driver;
    HomePage hp;
    LoginPage lp;
    MyAccountPage macc;

    List<HashMap<String, String>> datamap; //Data driven

    Logger logger; //for logging
    ResourceBundle rb; // for reading properties file
    String br; //to store browser name
	   
    @Before
    public void setup()    //Junit hook - executes once before starting
    {
        //for logging
        logger= LogManager.getLogger(this.getClass());
        //Reading config.properties (for browser)
        rb=ResourceBundle.getBundle("config");
        br=rb.getString("browser");
     
    }
    
    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario status ======>"+scenario.getStatus());
        if(scenario.isFailed()) {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName()); // attaches directly to Testcase
        	            
        }
       driver.quit();
    }
       
	@Given("User Launch browser")
	public void user_launch_browser() {
        if(br.equals("chrome"))
        {
           driver=new ChromeDriver();
        }
        else if (br.equals("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (br.equals("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("Opens URL {string}")
	public void opens_url(String url) {
        driver.get(url);
        driver.manage().window().maximize();
	}

	@When("User Navigate to MyAccount menu")
	public void user_navigate_to_my_account_menu() {
    	hp=new HomePage(driver);
    	hp.clickmyAccount();
        logger.info("Clicked on My Account ");
	}

	@When("click on Login")
	public void click_on_login() {
        hp.clickLogin();
        logger.info("Clicked on Login ");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) {
    	lp=new LoginPage(driver);
        
    	lp.setEmail(email);
        logger.info("Provided Email ");
        lp.setPassword(pwd);
        logger.info("Provided Password ");
	}

	@When("Click on Login button")
	public void click_on_login_button() {
        lp.clickLogin();
        logger.info("Clicked on Login button");
	}

	@Then("User Navigates to MyAccount Page")
	public void user_navigates_to_my_account_page() {
    	macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
	
        if(targetpage)
        {
            logger.info("Login Success ");
            Assert.assertTrue(true);
        }
        else
        {
            logger.error("Login Failed ");
            Assert.assertTrue(false);
        }
	}
	
	// ************************* Data Driven Test Method ***********************************
	@Then("check User navigates to MyAccount Page by passing Email and Password with excel row {string}")
	public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_row(String rows) throws InterruptedException {


		datamap = DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "sheet1");
		
		int index = Integer.parseInt(rows)-1;
		String email = datamap.get(index).get("username");
		String pwd = datamap.get(index).get("password");
		String exp_res = datamap.get(index).get("res");
		
		lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);		
		lp.clickLogin();
		
		try {
			macc = new MyAccountPage(driver);
    	
			boolean targetpage = macc.isMyAccountPageExists();
		
			if(exp_res.equals("Valid"))
			{
				if( targetpage == true) 
				{
					MyAccountPage myaccpage = new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(true);
				}
			
				else
				{
					Assert.assertTrue(false);
				}
			}
        
			if(exp_res.equals("Invalid"))
			{
				if(targetpage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		driver.close();
	}
	
	// *********************** Account Registration Methods *****************************

}
