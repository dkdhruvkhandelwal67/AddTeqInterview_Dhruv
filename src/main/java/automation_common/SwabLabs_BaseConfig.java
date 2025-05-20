package automation_common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SwabLabs_BaseConfig {

	WebDriver driver ;


	/**
	 * Configure driver
	 * @param browserName
	 */
	public WebDriver setupBaseDriver(String browserName)
	{

		if(browserName.equalsIgnoreCase("chrome"))
		{
			/**
			 * Add chromedriver configuration is need using chrome options
			 */
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--guest");


			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/driver/chromedriver.exe");
			driver = new ChromeDriver(options);
			SwabLabs_Logger.info("Browser Configured");
			driver.manage().window().maximize();
		}

		if(browserName.equalsIgnoreCase("firefox"))
		{
			//This can be configured in same way for this test we are just using Chrome
		}

		return driver;

	}

}
