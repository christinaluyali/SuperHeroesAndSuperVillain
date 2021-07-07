package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeaderboardPage {

	WebDriver driver;
	HashMap<String, Integer> userScores = new HashMap<String, Integer>();
	
	public LeaderboardPage(WebDriver driver) {
		this.driver = driver;
		// driver.get("https://responsivefight.herokuapp.com/leaderboard");
	}
	
	public int getScore(String username) {
		
		for (int i = 2; i < driver.findElements(By.xpath("//*[@id=\"showData\"]/table/tbody/tr")).size(); i++) {
			String userName = driver.findElement(By.xpath("//*[@id=\"showData\"]/table/tbody/tr[" + i + "]/td[2]")).getText();
			String score = driver.findElement(By.xpath("//*[@id=\"showData\"]/table/tbody/tr[" + i + "]/td[3]")).getText();
			userScores.put(userName, Integer.valueOf(score));
		}
		
		return userScores.get(username);
	}
}
