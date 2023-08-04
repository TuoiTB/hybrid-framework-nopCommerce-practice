package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegisterPageObject_pageFactory extends BasePageFactory {
	private WebDriver driver;
	
	public RegisterPageObject_pageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//UI
	@CacheLookup
	@FindBy(xpath = "//button[@id='register-button']")
	WebElement registerButton ;
	
	
	@FindBy(xpath = "//span[@id='FirstName-error']")
	WebElement firstNameErrorMessage;
	
	@FindBy(xpath = "//span[@id='LastName-error']")
	WebElement lastNameErrorMessage ;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	WebElement emailErrorMessage;
	
	@FindBy(xpath = "//span[@id='Password-error']")
	WebElement passwordErrorMessage;
	
	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	WebElement confirmPasswordErrorMessage;
	
	@FindBy(xpath = "//div[@class='result']")
	WebElement registerSuccessMessage;
	
	
	@FindBy(xpath = "//input[@id='FirstName']")
	WebElement firstNameTextbox;
	
	@FindBy(xpath = "//input[@id='LastName']")
	WebElement lastNameTextbox;
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@id='Password']")
	WebElement passwordTextbox;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	WebElement confirmPasswordTextbox;
	
	
	@FindBy(xpath = "//div[@class='header-logo']")
	WebElement homepageLogoImage;
	
	//Action
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	public void clickToHomePageLogo() {
		waitForElementClickable(driver, homepageLogoImage);
		clickToElement(driver, homepageLogoImage);
	}

	public void enterToFirstNameTextbox(String firstname) {
		waitForElementVisible(driver, firstNameTextbox);
		sendkeyToElement(driver, firstNameTextbox, firstname);
	}

	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendkeyToElement(driver, lastNameTextbox, lastName);
		
	}

	public void enterToEmailTextbox(String emailAdress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, emailAdress);
		
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
		
	}

	public void enterToConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendkeyToElement(driver, confirmPasswordTextbox, password);
	}

}
