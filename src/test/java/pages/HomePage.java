package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	@FindBy(css = "input#worrior_username")
	WebElement warriorName;

	@FindBy(css = "a#warrior")
	WebElement createWarriorButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://responsivefight.herokuapp.com");
		PageFactory.initElements(driver, this);
	}

	public void createWarrior(String name) {
		warriorName.sendKeys(name);
		createWarriorButton.click();
	}

	public void clickStart() {
		WebElement startButton = driver.findElement(By.cssSelector("#start"));
		startButton.click();
	}

	public String getWelcomeText() {
		WebElement welcomeMessage = driver.findElement(By.cssSelector("p#welcome_text"));
		return welcomeMessage.getText();
	}

	public boolean isOpen() {
		return driver.getCurrentUrl().equals("https://responsivefight.herokuapp.com/");
	}
}
