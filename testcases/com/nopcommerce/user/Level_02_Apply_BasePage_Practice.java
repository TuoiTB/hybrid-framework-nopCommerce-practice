package com.nopcommerce.user;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePagePractice;

public class Level_02_Apply_BasePage_Practice extends BasePagePractice{
	// Requirement:
	// Code to can run ok
	// No need optimize
	// Can repeat many same steps
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName;
	String lastName;
	Random rand = new Random();
	String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
	String password;

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		firstName = "John";
		lastName = "Wick";
		password = "auto@123";
	}

	@Test
	public void Register_01_Empty_Data() {
		openUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");

		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertTrue(
				getElementText(driver, "//span[@id='FirstName-error']").equals("First name is required."));
		Assert.assertTrue(
				getElementText(driver, "//span[@id='LastName-error']").equals("Last name is required."));
		Assert.assertTrue(
				getElementText(driver, "//span[@id='Email-error']").equals("Email is required."));
		Assert.assertTrue(
				getElementText(driver, "//span[@id='Password-error']").equals("Password is required."));
		Assert.assertTrue(getElementText(driver, "//span[@id='ConfirmPassword-error']")
				.equals("Password is required."));
	}

	@Test
	public void Register_02_Invalid_Email() {
		openUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		sendkeyToElement(driver, "//input[@id='Email']", "tuoi@gamil@com");
		sendkeyToElement(driver, "//input[@id='Password']", password);
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);

		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertTrue(getElementText(driver, "//span[@id='Email-error']").equals("Wrong email"));
	}

	@Test
	public void Register_03_Invalid_Password() {
		openUrl(driver, "https://demo.nopcommerce.com/");
		
		clickToElement(driver, "//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']",firstName );
		sendkeyToElement(driver, "//input[@id='LastName']",lastName );
		sendkeyToElement(driver, "//input[@id='Email']",emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']","12" );
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']","12" );
		
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertTrue(getElementText(driver, "//span[@id='Password-error']").equals("Password must meet the following rules:\nmust have at least 6 characters"));
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		openUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", password);
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "124446");
		
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertTrue(getElementText(driver, "//span[@id='ConfirmPassword-error']").equals("The password and confirmation password do not match."));
	}

	@Test
	public void Register_05_Success() {
		openUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']",firstName );
		sendkeyToElement(driver, "//input[@id='LastName']",lastName );
		sendkeyToElement(driver, "//input[@id='Email']",emailAddress );
		sendkeyToElement(driver, "//input[@id='Password']",password );
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']",password );
		
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
