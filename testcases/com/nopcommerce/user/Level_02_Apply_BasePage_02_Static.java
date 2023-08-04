package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Apply_BasePage_02_Static {

	WebDriver driver;
	BasePage basePage= BasePage.getBasePage();;
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
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		firstName = "John";
		lastName = "Wick";
		password = "auto@123";
		
	}
	@Test
	public void Register_01_Empty_Data() {
		//driver.get("https://demo.nopcommerce.com/");
		basePage.openUrl(driver, "https://demo.nopcommerce.com/");

		//driver.findElement(By.cssSelector("a.ico-register")).click();
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		//driver.findElement(By.cssSelector("button#register-button")).click();
		basePage.clickToElement(driver, "//button[@id='register-button']");

		//getText: basePage.getElementText(driver, "//span[@id='FirstName-error']");
		Assert.assertTrue(
				basePage.getElementText(driver, "//span[@id='FirstName-error']").equals("First name is required."));
		Assert.assertTrue(
				basePage.getElementText(driver, "//span[@id='LastName-error']").equals("Last name is required."));
		Assert.assertTrue(
				basePage.getElementText(driver, "//span[@id='Email-error']").equals("Email is required."));
		Assert.assertTrue(
				basePage.getElementText(driver, "//span[@id='Password-error']").equals("Password is required."));
		Assert.assertTrue(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']")
				.equals("Password is required."));
	}

	@Test
	public void Register_02_Invalid_Email() {
		//driver.get("https://demo.nopcommerce.com/");
		basePage.openUrl(driver, "https://demo.nopcommerce.com/");
		
		//driver.findElement(By.cssSelector("a.ico-register")).click();
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		basePage.sendkeyToElement(driver, "//input[@id='Email']", "tuoi@gamil@com");
		basePage.sendkeyToElement(driver, "//input[@id='Password']", password);
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);

		//driver.findElement(By.cssSelector("button#register-button")).click();
		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertTrue(basePage.getElementText(driver, "//span[@id='Email-error']").equals("Wrong email"));
		
	}

	@Test
	public void Register_03_Invalid_Password() {
		basePage.openUrl(driver, "https://demo.nopcommerce.com/");
		
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "12");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "12");
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertTrue(basePage.getElementText(driver, "//span[@id='Password-error']").equals("Password must meet the following rules:\nmust have at least 6 characters"));
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		basePage.openUrl(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", password);
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "124446");
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		
		Assert.assertTrue(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']").equals("The password and confirmation password do not match."));
	}

	@Test
	public void Register_05_Success() {
		basePage.openUrl(driver, "https://demo.nopcommerce.com/");

		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", password);
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
