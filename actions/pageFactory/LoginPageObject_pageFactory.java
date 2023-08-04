package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageObject_pageFactory extends BasePageFactory{
	private WebDriver driver;
	public LoginPageObject_pageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//UI
	@CacheLookup
	@FindBy(id = "Email")
	WebElement emailTextbox;
	
	@FindBy(id = "Password")
	WebElement passwordTextbox;
	
	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	WebElement loginButton;
	
	
	
	
	
	//Action
	public void enterToEmailTextbox(String emailAdress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, "value");
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	
	

}
