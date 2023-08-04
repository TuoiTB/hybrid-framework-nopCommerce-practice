package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUI.HomePageUI;
import pageUI.RegisterPageUI;

public class HomePageObject_pageFactory extends BasePageFactory{
	//Contains actions as functions of that page. Ex: Click/ Select/ Verify/ getText/... 
	private WebDriver driver;
	
	public HomePageObject_pageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//UI
	@CacheLookup
	@FindBy(className = "ico-register")
	WebElement registerLink;
	
	@FindBy(className = "ico-login")
	WebElement loginLink;
	
	@FindBy(className = "ico-account")
	WebElement myAccountLink;
	
	
	
	
	
	
	//Action
	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink);
	}

	public void clickToLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(driver, loginLink);
	}

	public void clickToMyAccountLink() {
		waitForElementClickable(driver, myAccountLink);
		clickToElement(driver, myAccountLink);
	}

	
}
