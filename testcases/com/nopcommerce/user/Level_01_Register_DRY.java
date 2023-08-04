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

public class Level_01_Register_DRY {
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

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		firstName = "John";
		lastName = "Wick";
		password = "auto@123";
	}

	@Test
	public void Register_01_Empty_Data() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertTrue(
				driver.findElement(By.cssSelector("span#FirstName-error")).getText().equals("First name is required."));
		Assert.assertTrue(
				driver.findElement(By.cssSelector("span#LastName-error")).getText().equals("Last name is required."));
		Assert.assertTrue(
				driver.findElement(By.cssSelector("span#Email-error")).getText().equals("Email is required."));
		Assert.assertTrue(
				driver.findElement(By.cssSelector("span#Password-error")).getText().equals("Password is required."));
		Assert.assertTrue(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText()
				.equals("Password is required."));
	}

	@Test
	public void Register_02_Invalid_Email() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#Email")).sendKeys("tuoi@gamil@com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("span#Email-error")).getText().equals("Wrong email"));
	}

	@Test
	public void Register_03_Invalid_Password() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("12");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("12");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#Password-error")).getText().equals("Password must meet the following rules:\nmust have at least 6 characters"));
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("124446");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText().equals("The password and confirmation password do not match."));
	}

	@Test
	public void Register_05_Success() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
