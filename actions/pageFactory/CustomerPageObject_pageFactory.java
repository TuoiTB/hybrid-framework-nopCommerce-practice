package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageUI.CustomerPageUI;

public class CustomerPageObject_pageFactory extends BasePageFactory{

	WebDriver driver;

	public CustomerPageObject_pageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//UI
	@CacheLookup
	@FindBy(id = "FirstName")
	WebElement firstNameTextbox;
	
	@FindBy(id = "LastName")
	WebElement lastNameTextbox;
	
	@FindBy(css = "input#Email")
	WebElement emailTextbox;
	
	@FindBy(css = "input#login")
	WebElement loginButton;
	
	//Action
	public void clickToLoginButton() {
		clickToElement(driver, loginButton);
	}
	
	public String getFirstNameAttributeValue() {
		waitForElementVisible(driver, firstNameTextbox);
		return getElementAttribute(driver, firstNameTextbox, "value");
	}

	public String getLastNameAttributeValue() {
		waitForElementVisible(driver, lastNameTextbox);
		return getElementAttribute(driver, lastNameTextbox, "value");
	}

	public String getEmailAttributeValue() {
		waitForElementVisible(driver, emailTextbox);
		return getElementAttribute(driver, emailTextbox, "value");
	}
}
