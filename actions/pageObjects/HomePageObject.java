package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.BasePage;
import pageUI.HomePageUI;
import pageUI.RegisterPageUI;

public class HomePageObject extends BasePage{
	//Contains actions as functions of that page. Ex: Click/ Select/ Verify/ getText/... 
	private WebDriver driver;
	WebDriverWait explicitWait;
	
	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	

	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public CustomerPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerPage(driver);
	}

	
}
