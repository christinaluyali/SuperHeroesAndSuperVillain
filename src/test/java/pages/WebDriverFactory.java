package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
	
	WebDriver driver;
	
	public WebDriverFactory(String browser) {
	
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe"); 
			driver=new InternetExplorerDriver();
		}
	}
		
	public WebDriver getWebDriver() {
		return driver;
	}
}
