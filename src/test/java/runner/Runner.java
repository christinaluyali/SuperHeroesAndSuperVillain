package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "html:target/cucumber-reports/firefoxtest.html", "json:target/cucumber/firefoxtest.json" },
		features = ".\\src\\test\\java\\features", 
		glue = { "stepdefinitions" }
		, tags = "@Firefox"
)

public class Runner {
}