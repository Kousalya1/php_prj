package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import pageObjects.BasePage;
import utilities.TestContext;

public class Hooks extends BasePage {

	public Hooks(TestContext context) {
		super(context);
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("--------------------------------------------------");
		System.out.println("Execution started for scenario:'" + scenario.getName() + "'");
	}

	@After
	public void afterScenario(Scenario scenario) throws InterruptedException {
		driverManager.quitBrowser();
		System.out.println("--------------------------------------------------");
		System.out.println("Execution completed for scenario:'" + scenario.getName() + "'");
	}
}