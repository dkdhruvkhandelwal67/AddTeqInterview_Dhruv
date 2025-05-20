package automation_pagetest;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation_common.SwabLabs_BaseConfig;
import automation_common.SwabLabs_SupportingCapabilities;
import automation_pageobjects.SwabLabs_PageObjectFirst;


public class SwabLabs_PageTestParalleltest extends SwabLabs_BaseConfig{

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


	@DataProvider (name = "data-provider")
	public Object[][] dpMethod() throws IOException, ParseException{

		JSONParser jsonParser= new JSONParser();
		FileReader fileReader = new FileReader(System.getProperty("user.dir")+ "/src/test/resources/userDetails.json");
		//Read Json file
		Object obj = jsonParser.parse(fileReader);
		JSONObject jsonObject = (JSONObject)obj;

		return new Object[][] {
			{jsonObject.get("UserName1"), jsonObject.get("Password1")},
			{jsonObject.get("UserName2"), jsonObject.get("Password2")},
			{jsonObject.get("UserName3"), jsonObject.get("Password3")}
		};
	}

	@Test (dataProvider = "data-provider" , priority =5)
	public void myTest (String userName, String password) {
		
		/**
		 * Here we have implemented sample data provider as part of the interview requirement , the extract data can we used is any login test
		 */
		System.out.println(userName + "  "+password) ;
	}

	@AfterTest
	@Parameters({"browserName"})
	public void closeDriver()
	{
		driver.quit();
	}

}
