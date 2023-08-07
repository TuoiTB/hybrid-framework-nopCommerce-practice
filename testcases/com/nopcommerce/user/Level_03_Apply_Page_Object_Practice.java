package com.nopcommerce.user;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePagePractice;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_03_Apply_Page_Object_Practice{

	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	
	
	private String firstName;
	private String lastName;
	Random rand = new Random();
	private String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
	private String password;
	
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	
		firstName = "John";
		lastName = "Wick";
		password = "auto@123";
		driver.get("https://demo.nopcommerce.com/");
		
	}
	@Test
	public void Register_01_Empty_Data() {
		
		homePage = new HomePageObject(driver);
		
		homePage.clickToRegisterLink();
		
		//Khởi tạo registerPage lên để chạy tiếp flow
		registerPage = new RegisterPageObject(driver);
		
		registerPage.clickToRegisterButton();

		
		Assert.assertTrue(
				registerPage.getFirstNameErrorMessage().equals("First name is required."));
		Assert.assertTrue(
				registerPage.getLastNameErrorMessage().equals("Last name is required."));
		Assert.assertTrue(
				registerPage.getEmailErrorMessage().equals("Email is required."));
		Assert.assertTrue(
				registerPage.getPasswordErrorMessage().equals("Password is required."));
		Assert.assertTrue(registerPage.getConfirmPasswordErrorMessage()
				.equals("Password is required."));
	}

	@Test
	public void Register_02_Invalid_Email() {
		registerPage.clickToHomePageLogo();
		
		homePage = new HomePageObject(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox("tuoi@gmail@com");
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();

		Assert.assertTrue(registerPage.getEmailErrorMessage().equals("Wrong email"));
		
	}

	@Test
	public void Register_03_Invalid_Password() {
		registerPage.clickToHomePageLogo();
		
		homePage = new HomePageObject(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("1234");
		registerPage.enterToConfirmPasswordTextbox("1234");
		
		registerPage.clickToRegisterButton();
		
		Assert.assertTrue(registerPage.getPasswordErrorMessage().equals("Password must meet the following rules:\nmust have at least 6 characters"));
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		registerPage.clickToHomePageLogo();
		
		homePage = new HomePageObject(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox("1234");
		
		registerPage.clickToRegisterButton();
		
		Assert.assertTrue(registerPage.getConfirmPasswordErrorMessage().equals("The password and confirmation password do not match."));
	}

	@Test
	public void Register_05_Success() {
		registerPage.clickToHomePageLogo();
		
		homePage = new HomePageObject(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		registerPage.clickToHomePageLogo();
		
		homePage = new HomePageObject(driver);
		
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		
		homePage.clickToMyAccountLink();
		
		customerPage = new CustomerPageObject(driver);
		
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(),firstName);
		Assert.assertEquals(customerPage.getLastNameAttributeValue(),lastName);
		Assert.assertEquals(customerPage.getEmailAttributeValue(),emailAddress);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
