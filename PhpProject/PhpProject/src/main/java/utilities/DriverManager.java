package utilities;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import dataProviders.EnvPropertyManager;

public class DriverManager {

	private WebDriver driver;
	
	public WebDriver getDriver(DriverType driverType) throws Exception {
		try {
			switch (driverType) {
			case FIREFOX:
				System.setProperty(EnvPropertyManager.getFirefoxDriver(), EnvPropertyManager.getFirefoxDriverPath());
				driver = new FirefoxDriver();
				break;
			case CHROME:
				System.setProperty(EnvPropertyManager.getChromeDriver(), EnvPropertyManager.getChromeDriverPath());
				driver = new ChromeDriver();
				break;
			case HEADLESS:
				ChromeOptions option = new ChromeOptions();
				System.setProperty(EnvPropertyManager.getChromeDriver(), EnvPropertyManager.getChromeDriverPath());
				option.addArguments("headless");
				driver = new ChromeDriver(option);
				break;
			default:
				System.setProperty(EnvPropertyManager.getChromeDriver(), EnvPropertyManager.getChromeDriverPath());
				driver = new ChromeDriver();
				break;
			}
			maximizeWindow();
			driver.manage().timeouts().pageLoadTimeout(EnvPropertyManager.getImplicitWaitInMilliSeconds(),
					TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			throw new Exception("Driver is not initialized for driver type " + driverType.toString() + e);
		}
		return driver;
	}

	private void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public void quitBrowser() {
		driver.quit();
	}

}
