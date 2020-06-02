package utilities;

import org.openqa.selenium.WebDriver;

import dataProviders.EnvPropertyManager;

public class TestContext {

	private WebDriver driver;
	private DriverManager driverManager;
	private ElementFactory elementFactory;

	public TestContext() {
		driverManager = new DriverManager();
		try {
			driver = driverManager.getDriver(EnvPropertyManager.getBrowser());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public DriverManager getDriverManager() {
		return driverManager;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public ElementFactory getElementFactory() {
		return (elementFactory == null) ? elementFactory = new ElementFactory(driver) : elementFactory;
	}
	
}
