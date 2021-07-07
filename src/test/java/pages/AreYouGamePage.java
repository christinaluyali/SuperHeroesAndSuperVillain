package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AreYouGamePage extends GamePage {

	public AreYouGamePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOpen() {
		return driver.getCurrentUrl().equals("https://responsivefight.herokuapp.com/news");
	}

	public String getMessageText() {
		
		WebElement messageText;
		if (isIntroModalVisible()) {
			messageText = driver.findElement(By.xpath("//*[@id='introModal']/div/div/div[1]/h5"));
		} else if (isCorrectModalVisible()) {
			messageText = driver.findElement(By.xpath("//*[@id='correctModal']/div/div/div[1]/h5"));
		} else {
			messageText = driver.findElement(By.xpath("//*[@id='incorrectModal']/div/div/div[1]/h5"));
		}
		return messageText.getText();
	}
	
	public boolean isIntroModalVisible() {
		WebElement introModal = driver.findElement(By.cssSelector("div#introModal"));
		return introModal.isDisplayed();
	}
	
	public boolean isCorrectModalVisible() {
		WebElement correctModal = driver.findElement(By.cssSelector("div#correctModal"));
		return correctModal.isDisplayed();
	}

	public boolean isIncorrectModalVisible() {
		WebElement incorrectModal = driver.findElement(By.cssSelector("div#incorrectModal"));
		return incorrectModal.isDisplayed();
	}

	public void clickAnswer1() {
		WebElement answer1 = driver.findElement(By.cssSelector("a#answer_1"));
		answer1.click();
	}

	public void clickAnswer2() {
		WebElement answer2 = driver.findElement(By.cssSelector("a#answer_2"));
		answer2.click();
	}
	
	public void clickContinue() {
		WebElement continueButton = driver.findElement(By.cssSelector("button[id=continue]"));
		continueButton.click();
	}
	
	public void clickTryAgain() {
		WebElement tryAgain = driver.findElement(By.cssSelector("button#close_modal_btn_2"));
		tryAgain.click();
	}
}
