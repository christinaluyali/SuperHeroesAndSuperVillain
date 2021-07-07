package stepdefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AreYouGamePage;
import pages.BusGamePage;
import pages.GamePage;
import pages.HomePage;
import pages.LandingPage;
import pages.LeaderboardPage;
import pages.OfficeGamePage;
import pages.RestaurantGamePage;
import pages.WebDriverFactory;

public class GameStepDefinitions {

	WebDriver driver;
	String pageName;
	HomePage homepage;
	LandingPage landingPage;
	LeaderboardPage leaderboardPage;
	OfficeGamePage officePage;
	AreYouGamePage gamePage;
	RestaurantGamePage restaurantPage;
	BusGamePage busPage;
	GamePage currentPage;
	
	String warriorName;

	@Before("@Firefox")
	public void i_open_firefox() {
		WebDriverFactory webDriverFactory = new WebDriverFactory("firefox");
		driver = webDriverFactory.getWebDriver();
	}
	
	@Before("@Chrome")
	public void i_open_chrome() {
		WebDriverFactory webDriverFactory = new WebDriverFactory("chrome");
		driver = webDriverFactory.getWebDriver();
	}
	
	@Before("@InternetExplorer")
	public void i_open_ie() {
		WebDriverFactory webDriverFactory = new WebDriverFactory("ie");
		driver = webDriverFactory.getWebDriver();
	}
	
	@After
	public void i_close_browser() {
		driver.close();
	}

	@Given("^I create warrior \"(.*)\"$")
	public void i_create_warrior(String warriorName) {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		this.warriorName = warriorName;
		
		homepage = new HomePage(driver);
		homepage.createWarrior(warriorName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#start")));
		homepage.clickStart();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#welcome_text")));
		landingPage = new LandingPage(driver);
	}
	
	@Then("^I verify the welcome message$") 
	public void i_verify_welcome_text() {
		
		if (landingPage.isOpen()) {
			Assert.assertTrue("Unexpected landing page text " + homepage.getWelcomeText(),
					homepage.getWelcomeText().equals("Choose your battle field " + warriorName));
		} else {
			Assert.fail("Failed to open landing page");
		}
	}
	
	@When("^I go to page \"(.*)\"$")
	public void i_go_to_page(String pageName) {
		
		this.pageName = pageName;
		switch (pageName.toUpperCase()) {
			case "ARE YOU GAME?": 
				landingPage.clickAreYouGame();
				gamePage = new AreYouGamePage(driver);
				Assert.assertTrue("Could not open page", gamePage.isOpen());
				currentPage = gamePage;
				break;
				
			case "OFFICE":
				landingPage.clickOffice();
				officePage = new OfficeGamePage(driver); 
				Assert.assertTrue("Could not open page", officePage.isOpen());
				currentPage = officePage;
				break;
				
			case "RESTAURANT":
				landingPage.clickRestaurant();
				restaurantPage = new RestaurantGamePage(driver);
				Assert.assertTrue("Could not open page", restaurantPage.isOpen());
				currentPage = restaurantPage;
				break;
				
			case "BUS":
				landingPage.clickBus();
				busPage = new BusGamePage(driver);
				Assert.assertTrue("Could not open page", busPage.isOpen());
				currentPage = busPage;
				break;
				
			default:
				Assert.fail("Unknown page " + pageName);
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@When("^I select the correct answer$")
	public void i_select_the_correct_answer() throws InterruptedException {

		switch (pageName.toUpperCase()) {
			case "ARE YOU GAME?": 
				gamePage.clickAnswer1();
				break;
			case "OFFICE":
				officePage.clickAnswer1();
				break;
			case "RESTAURANT":
				restaurantPage.clickAnswer1();
				break;
			case "BUS":
				busPage.clickAnswer1();
				break;
			default:
				Assert.fail("Unknown page " + pageName);
		}
		Thread.sleep(2000);
	}
	
	@When("^I select the incorrect answer$")
	public void i_click_the_incorrect_answer() throws InterruptedException {

		switch (pageName.toUpperCase()) {
			case "ARE YOU GAME?": 
				gamePage.clickAnswer2();
				break;
			case "OFFICE":
				officePage.clickAnswer2();
				break;
			case "RESTAURANT":
				restaurantPage.clickAnswer2();
				break;
			case "BUS":
				busPage.clickAnswer2();
				break;
			default:
				Assert.fail("Unknown page " + pageName);
		}
		Thread.sleep(2000);
	}
	
	@When("^I wait for timer to time out$")
	public void i_wait_for_timer_to_time_out() throws InterruptedException {
		Thread.sleep(30000);
	}
	
	@When("^I click the start button$")
	public void i_click_the_start_button() throws InterruptedException {
		currentPage.clickStart();
		Thread.sleep(2000);
	}
	
	@When("^I get my score and verify the leaderboard$")
	public void i_go_to_the_leaderboard_and_verify_score() {
		
		int expectedScore = 0;
		
		switch (pageName.toUpperCase()) {
			case "OFFICE":
				expectedScore = officePage.getScore();
				officePage.clickCheckFinalScore();
				break;
			case "RESTAURANT":
				expectedScore = restaurantPage.getScore();
				restaurantPage.clickCheckFinalScore();
				break;
			case "BUS":
				expectedScore = busPage.getScore();
				busPage.clickCheckFinalScore();
				break;
			default:
				Assert.fail("Could not navigate to the leaderboard " + pageName);
		}
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#showData")));
		} catch (Exception e) {
			Assert.fail("Could not check score on leaderboard");
		}

		leaderboardPage = new LeaderboardPage(driver);
		
		Assert.assertTrue(warriorName + " expected score: " + expectedScore + " but got: " + leaderboardPage.getScore(warriorName), leaderboardPage.getScore(warriorName) == expectedScore);
		
	}
	
	
	@Then("I verify \"(.*)\" message is displayed$")
	public void i_verify_message_is_displayed(String expectedText) {

		String messageText = null;
		switch (pageName.toUpperCase()) {
			case "ARE YOU GAME?": 
				messageText = gamePage.getMessageText();
				break;
			case "OFFICE":
				messageText = officePage.getMessageText();
				break;
			case "RESTAURANT":
				messageText = restaurantPage.getMessageText();
				break;
			case "BUS":
				messageText = busPage.getMessageText();
				break;
			default:
				Assert.fail("Unknown page " + pageName);
		}
		
		Assert.assertTrue("Expected message: " + expectedText + " but got: " + messageText, messageText.equals(expectedText));
	}

}
