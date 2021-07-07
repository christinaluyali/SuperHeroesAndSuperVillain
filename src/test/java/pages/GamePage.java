package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GamePage {

	WebDriver driver;

	public void clickStart() {
		WebElement startButton = driver.findElement(By.cssSelector("button[id*=start]"));
		startButton.click();
	}
	
	public void clickAnswer1() {
		WebElement answer1 = driver.findElement(By.cssSelector("a[id*=answer_1]"));
		answer1.click();
	}

	public void clickAnswer2() {
		WebElement answer2 = driver.findElement(By.cssSelector("a[id*=answer_2]"));
		answer2.click();
	}
	
	public void clickCheckFinalScore() {
		WebElement finalScore = driver.findElement(By.cssSelector("button#leaderboard_link"));
		finalScore.click();
	}
	
	
	public void clickTryNextBattle() {
		WebElement tryAgain = driver.findElement(By.cssSelector("button#close_modal_btn_1"));
		tryAgain.click();
	}
}
