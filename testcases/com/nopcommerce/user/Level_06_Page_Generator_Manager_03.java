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

import commons.BasePage;
import commons.BaseTest;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class Level_06_Page_Generator_Manager_03 extends BaseTest {

	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	
	
	private String firstName = "John";;
	private String lastName = "Wick";;
	Random rand = new Random();
	private String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
	private String password = "auto@123";;
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}
	@Test
	public void Register_01_Empty_Data() {
		
		registerPage = homePage.clickToRegisterLink();
		
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
		
		homePage = registerPage.clickToHomePageLogo();
		
		registerPage = homePage.clickToRegisterLink();
		
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
		
		homePage = registerPage.clickToHomePageLogo();
		
		registerPage = homePage.clickToRegisterLink();
		
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
		
		
		homePage = registerPage.clickToHomePageLogo();
		
		registerPage = homePage.clickToRegisterLink();
		
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
		
		homePage = registerPage.clickToHomePageLogo();
		
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickToHomePageLogo();
		
		loginPage = homePage.clickToLoginLink();
		
		homePage = loginPage.loginAsUser(emailAddress, password);
		
		customerPage = homePage.clickToMyAccountLink();
		
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(),firstName);
		Assert.assertEquals(customerPage.getLastNameAttributeValue(),lastName);
		Assert.assertEquals(customerPage.getEmailAttributeValue(),emailAddress);
		
	}

	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}

}
