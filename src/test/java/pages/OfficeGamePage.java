package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OfficeGamePage extends GamePage {
	
	public OfficeGamePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOpen() {
		return driver.getCurrentUrl().equals("https://responsivefight.herokuapp.com/office");
	}

	public String getMessageText() {
		
		WebElement messageText;
		if (isIntroModalVisible()) {
			messageText = driver.findElement(By.xpath("//*[@id='staticBackdrop']/div/div/div[1]/h5"));
		} else if (isCorrectModalVisible()) {
			messageText = driver.findElement(By.xpath("//*[@id='staticBackdrop2']/div/div/div[1]/h5"));
		} else if (isIncorrectModalVisible()){
			messageText = driver.findElement(By.xpath("//*[@id='staticBackdrop3']/div/div/div[1]/h5"));
		} else {
			messageText = driver.findElement(By.xpath("//*[@id='staticBackdrop4']/div/div/div[1]/h5"));
		}
		return messageText.getText();
	}
	
	public boolean isIntroModalVisible() {
		WebElement introModal = driver.findElement(By.cssSelector("div#staticBackdrop"));
		return introModal.isDisplayed();
	}
	
	public boolean isCorrectModalVisible() {
		WebElement correctModal = driver.findElement(By.cssSelector("div#staticBackdrop2"));
		return correctModal.isDisplayed();
	}

	public boolean isIncorrectModalVisible() {
		WebElement incorrectModal = driver.findElement(By.cssSelector("div#staticBackdrop3"));
		return incorrectModal.isDisplayed();
	}
	
	public boolean isOutOfTimeModalVisible() {
		WebElement outOfTimeModal = driver.findElement(By.cssSelector("div#staticBackdrop4"));
		return outOfTimeModal.isDisplayed();
	}
	
	public int getScore() {
		WebElement score = driver.findElement(By.cssSelector("p#score"));
		return Integer.valueOf(score.getText().split(" ")[score.getText().split(" ").length-2]);
	}
	
	public void clickTryAgain() {
		WebElement tryAgain = driver.findElement(By.cssSelector("button#close_modal_btn_2"));
		tryAgain.click();
	}
}
