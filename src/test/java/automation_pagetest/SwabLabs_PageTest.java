package automation_pagetest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation_common.SwabLabs_BaseConfig;
import automation_common.SwabLabs_SupportingCapabilities;
import automation_pageobjects.SwabLabs_PageObjectFirst;


public class SwabLabs_PageTest extends SwabLabs_BaseConfig{

	/**
	 * variables
	 */
	static WebDriver driver;
	SwabLabs_BaseConfig base = new SwabLabs_BaseConfig();
	private SwabLabs_SupportingCapabilities capabilities = new SwabLabs_SupportingCapabilities();
	private final static  String username = null;
	private final static  String password = null; 
	String Url =capabilities.getPropertyVal("Url", "testconfig.properties");
	SwabLabs_PageObjectFirst pageObj;

	@SuppressWarnings("deprecation")
	@BeforeTest
	@Parameters({"browserName"})
	public void setupdriver(String browserName) throws InterruptedException
	{
		this.driver = base.setupBaseDriver(browserName);
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		pageObj = new SwabLabs_PageObjectFirst(driver);
	}

	@Test (priority =1)
	public void verifyLoginWithIncorrectCredentials() throws InterruptedException
	{
		Boolean status = pageObj.loginWithInvalidCredentials();
		Assert.assertTrue(status, "Correct Error Details Are Not Shown For Invalid User");
	}

	@Test (priority =2)
	public void verifyLoginWithCorrectCredentials() throws InterruptedException
	{
		Boolean status = pageObj.verifyLoginWithCorrectCredentials();
		Assert.assertTrue(status, "User Not Able To Login Properly");

	}

	@Test (priority =3 ,dependsOnMethods = "verifyLoginWithCorrectCredentials")
	public void verifyCartFunctionalityWorksCorectly()
	{
		Boolean status = pageObj.verifyCartFunctionalityWorksCorectly();
		Assert.assertTrue(status, "Item Id Not Getting Added To Car Correctly");
	}

	@Test (priority =4 ,dependsOnMethods = "verifyLoginWithCorrectCredentials")
	public void verifyCheckoutFunctionalityWorksCorrectly()
	{
		Boolean status = pageObj.verifyCheckoutFunctionalityWorksCorrectly();
		Assert.assertTrue(status, "Checkout IS Not Working Correctly, User Is Unable To Place Order");
	}

	@AfterTest
	@Parameters({"browserName"})
	public void closeDriver()
	{
		driver.quit();
	}

}
