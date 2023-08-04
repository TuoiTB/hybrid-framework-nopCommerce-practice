package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.CustomerPageObject_pageFactory;
import pageFactory.HomePageObject_pageFactory;
import pageFactory.LoginPageObject_pageFactory;
import pageFactory.RegisterPageObject_pageFactory;


public class Level_05_Page_Factory extends BaseTest {

	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	private HomePageObject_pageFactory homePage;
	private RegisterPageObject_pageFactory registerPage;
	private LoginPageObject_pageFactory loginPage;
	private CustomerPageObject_pageFactory customerPage;
	
	
	private String firstName = "John";;
	private String lastName = "Wick";;
	Random rand = new Random();
	private String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
	private String password = "auto@123";;
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
	}
	@Test
	public void Register_01_Empty_Data() {
		
		homePage = new HomePageObject_pageFactory(driver);
		
		homePage.clickToRegisterLink();
		
		//Khởi tạo registerPage lên để chạy tiếp flow
		registerPage = new RegisterPageObject_pageFactory(driver);
		
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
		
		homePage = new HomePageObject_pageFactory(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject_pageFactory(driver);
		
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
		
		homePage = new HomePageObject_pageFactory(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject_pageFactory(driver);
		
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
		
		homePage = new HomePageObject_pageFactory(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject_pageFactory(driver);
		
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
		
		homePage = new HomePageObject_pageFactory(driver);
		
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject_pageFactory(driver);
		
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		registerPage.clickToHomePageLogo();
		
		homePage = new HomePageObject_pageFactory(driver);
		
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject_pageFactory(driver);
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject_pageFactory(driver);
		
		homePage.clickToMyAccountLink();
		
		customerPage = new CustomerPageObject_pageFactory(driver);
		
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(),firstName);
		Assert.assertEquals(customerPage.getLastNameAttributeValue(),lastName);
		Assert.assertEquals(customerPage.getEmailAttributeValue(),emailAddress);
		
	}

	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}

}
