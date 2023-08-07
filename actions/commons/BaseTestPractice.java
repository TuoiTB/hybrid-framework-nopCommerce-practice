package commons;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTestPractice {
	//Contains common function for all testcases
	//private String projectPath = System.getProperty("user.dir");
	private WebDriver driver;
	protected WebDriver getBrowserDriver (String browserName) {
		BrowserListPractice browserList = BrowserListPractice.valueOf(browserName.toUpperCase());
		switch (browserList) {
		
		case CHROME: 
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			
			//version 5.x
			//Tự tải chromedriver tương ứng với chrome browser, sau đó khởi tạo driver lên
			//driver = WebDriverManager.chromedriver().create();
			break;
			
		case FIREFOX: 
			//driver = WebDriverManager.firefoxdriver().create();
			driver = new FirefoxDriver();
			break;
			
		case EDGE: 
			//driver = WebDriverManager.edgedriver().create();
			driver = new EdgeDriver();
			break;
		
		default:
			throw new RuntimeException("Browser name is not valid");
		}
		
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
}
	
	protected String getEmailAddres() {
		Random rand = new Random();
		return "john" + rand.nextInt(9999) + "@gmail.com";
	}
	
	protected void quitBrowserDriver() {
		if (driver !=null)
		{
			driver.quit();
		}
	}
}
