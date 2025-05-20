package automation_pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import automation_common.SwabLabs_Logger;
import automation_common.SwabLabs_SupportingCapabilities;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.*;

public class SwabLabs_PageObjectFirst {

	WebDriver driver;
	WebDriverWait wait;
	private SwabLabs_SupportingCapabilities capabilities = new SwabLabs_SupportingCapabilities();

	public SwabLabs_PageObjectFirst(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
		// Create a WebDriverWait instance
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}


	/**
	 * Locators
	 */

	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement userName;

	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement passWord;
	
	@FindBy(xpath="//h3[@data-test='error']")
	WebElement errorDetails;

	@FindBy(xpath="//input[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath="//div[contains(text(),'Sauce Labs Backpack')]")
	WebElement itemName;

	@FindBy(xpath="//*[@id=\"add-to-cart-sauce-labs-backpack\"]")
	WebElement addToCart;

	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement cartIcon;

	@FindBy(xpath="//div[@class='inventory_item_name']")
	WebElement inventoryAdded;

	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkOutButton;

	@FindBy(xpath="//*[@id=\"first-name\"]")
	WebElement firstNameVal;

	@FindBy(xpath="//*[@id=\"last-name\"]")
	WebElement lastNameVal;

	@FindBy(xpath="//*[@id=\"postal-code\"]")
	WebElement postalCodeVal;

	@FindBy(xpath="//*[@id=\"continue\"]")
	WebElement continueButton;

	@FindBy(xpath="//*[@id=\"finish\"]")
	WebElement finishButton;

	@FindBy(xpath="//h2[@class='complete-header']")
	WebElement finalMessage;


	/**
	 * Implementing methods
	 * @return 
	 * @throws InterruptedException 
	 */

	public boolean loginWithInvalidCredentials() throws InterruptedException
	{
		SwabLabs_Logger.info("Start Login for the Page with invalid credentials");
		userName.sendKeys(capabilities.getPropertyVal("invalidUsername", "testconfig.properties"));
		passWord.sendKeys(capabilities.getPropertyVal("passwordVal", "testconfig.properties"));
		loginButton.click();
		
		String errorVal = errorDetails.getText();
		return errorVal.equals("Epic sadface: Sorry, this user has been locked out.");
	}

	public boolean verifyLoginWithCorrectCredentials() throws InterruptedException
	{
		SwabLabs_Logger.info("Start Login for the Page with valid credentials");
		driver.navigate().refresh();
		Thread.sleep(3000);
		userName.sendKeys(capabilities.getPropertyVal("validUsername", "testconfig.properties"));
		passWord.sendKeys(capabilities.getPropertyVal("passwordVal", "testconfig.properties"));
		
		loginButton.submit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='shopping_cart_link']")));
		return cartIcon.isDisplayed();
	}

	public boolean verifyCartFunctionalityWorksCorectly()
	{
		SwabLabs_Logger.info("Start adding item to the cart");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Sauce Labs Backpack')]")));
		String itemNameVal=itemName.getText();
		addToCart.click();
		cartIcon.click();
		String inventoryAddeVal =inventoryAdded.getText();
		checkOutButton.click();
		
		return itemNameVal.equals(inventoryAddeVal);
	}

	public boolean verifyCheckoutFunctionalityWorksCorrectly()
	{
		SwabLabs_Logger.info("Start the checkout process to complete the purchase");
		firstNameVal.sendKeys("Dhruv");
		lastNameVal.sendKeys("Khandelwal");
		postalCodeVal.sendKeys("110011");
		continueButton.click();;
		finishButton.click();
		String finalMessageVal = finalMessage.getText();

		return finalMessageVal.equals("Thank you for your order!");
	}


}
