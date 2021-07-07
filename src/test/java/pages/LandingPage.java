package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;

	@FindBy(css = "a#news")
	WebElement areYouGame;

	@FindBy(css = "a#bus")
	WebElement bus;

	@FindBy(css = "a#restaurant")
	WebElement restaurant;

	@FindBy(css = "a#office")
	WebElement office;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickAreYouGame() {
		areYouGame.click();
	}

	public void clickBus() {
		bus.click();
	}

	public void clickRestaurant() {
		restaurant.click();
	}

	public void clickOffice() {
		office.click();
	}

	public void clickStart() {
		WebElement startButton = driver.findElement(By.cssSelector("a#start"));
		startButton.click();
	}

	public boolean isOpen() {
		return driver.getCurrentUrl().equals("https://responsivefight.herokuapp.com/covid");
	}

	public String getWelcomeText() {
		WebElement welcomeMessage = driver.findElement(By.cssSelector("p#welcome_text"));
		return welcomeMessage.getText();
	}
}
