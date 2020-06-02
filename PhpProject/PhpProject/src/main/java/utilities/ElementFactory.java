package utilities;

import java.time.Duration;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import dataProviders.EnvPropertyManager;

public class ElementFactory {

	private WebDriver driver;
	private Wait<WebDriver> wait;
	private Actions actionBuilder;

	public ElementFactory(WebDriver driver) {
		this.driver = driver;
		wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(EnvPropertyManager.getImplicitWaitInMilliSeconds()))
				.pollingEvery(Duration.ofMillis(EnvPropertyManager.getPollingIntervalInMilliSeconds()))
				.ignoring(NoSuchElementException.class);
	}

	public void elementClick(WebElement element) {
		try {
			waitUntilClickable(element);
			scrollIntoView(element);
			element.click();
		} catch (Exception e) {
			javaScriptExecutorClick(element);
		}
	}

	public void enterTextBox(WebElement element, String value) {
		waitUntilVisible(element);
		element.clear();
		if (!value.trim().isEmpty())
			element.sendKeys(value);
	}

	public void elementSelectDropDown(WebElement element, String optionValue) {
		if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
			new Select(element).selectByVisibleText(optionValue);
		}
	}

	public String elementGetText(WebElement element) {
		String returnText = "";
		if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
			if (element.isDisplayed() && element.isEnabled()) {
				returnText = element.getText();
			}
		}
		return returnText;
	}

	public void untilPageLoadComplete() {
		until((d) -> {
			return (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState")
					.equals("complete");
		});
	}
	
	public void until(Function<WebDriver, Boolean> waitCondition) {
		wait.until(waitCondition);
	}
	
	public void waitUntilClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void javaScriptExecutorClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void waitUntilVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void elementMouseHover(WebElement element, Boolean scroll) {
		if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
			if (element.isDisplayed() && element.isEnabled()) {
				if (scroll) {
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + element.getLocation().y + ")");
				}
				actionBuilder = new Actions(driver);
				actionBuilder.moveToElement(element).build();
				actionBuilder.perform();
			}
		}
	}
}