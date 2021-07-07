package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BusGamePage extends GamePage {

	public BusGamePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOpen() {
		return driver.getCurrentUrl().equals("https://responsivefight.herokuapp.com/bus");
	}

	public String getMessageText() {
		
		WebElement messageText;
		if (isIntroModalVisible()) {
			messageText = driver.findElement(By.xpath("//*[@id='bus_intro_modal']/div/div/div[1]/h5"));
		} else if (isCorrectModalVisible()) {
			messageText = driver.findElement(By.xpath("//*[@id='bus_correct_modal']/div/div/div[1]/h5"));
		} else if (isIncorrectModalVisible()) {
			messageText = driver.findElement(By.xpath("//*[@id='bus_incorrect_modal']/div/div/div[1]/h5"));
		} else {
			messageText = driver.findElement(By.xpath("//*[@id='bus_out_of_time_modal']/div/div/div[1]/h5"));
		}
		return messageText.getText();
	}
	
	public boolean isIntroModalVisible() {
		WebElement introModal = driver.findElement(By.cssSelector("div#bus_intro_modal"));
		return introModal.isDisplayed();
	}
	
	public boolean isCorrectModalVisible() {
		WebElement correctModal = driver.findElement(By.cssSelector("div#bus_correct_modal"));
		return correctModal.isDisplayed();
	}

	public boolean isIncorrectModalVisible() {
		WebElement incorrectModal = driver.findElement(By.cssSelector("div#bus_incorrect_modal"));
		return incorrectModal.isDisplayed();
	}
	
	public boolean isOutOfTimeModalVisible() {
		WebElement outOfTimeModal = driver.findElement(By.cssSelector("div#bus_out_of_time_modal"));
		return outOfTimeModal.isDisplayed();
	}
	
	public int getScore() {
		WebElement score = driver.findElement(By.cssSelector("p#score"));
		return Integer.valueOf(score.getText().split(" ")[score.getText().split(" ").length-2]);
	}
	
	public void clickTryAgain() {
		WebElement tryAgain = driver.findElement(By.cssSelector("button#close_correct_modal_btn"));
		tryAgain.click();
	}
	
}
