package pageObjects;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utilities.TestContext;

public class RegistrationPage extends BasePage  {

	@FindBy(how = How.ID, using = "inputFirstName")
	private WebElement firstName;

	@FindBy(how = How.ID, using = "inputLastName")
	private WebElement lastName;

	@FindBy(how = How.ID, using = "inputEmail")
	private WebElement emailAddress;

	@FindBy(how = How.ID, using = "inputPhone")
	private WebElement phoneNumber;

	@FindBy(how = How.ID, using = "inputCompanyName")
	private WebElement companyName;

	@FindBy(how = How.ID, using = "inputAddress1")
	private WebElement streetAddress;

	@FindBy(how = How.ID, using = "inputAddress2")
	private WebElement streetAddress2;

	@FindBy(how = How.ID, using = "inputCity")
	private WebElement city;

	@FindBy(how = How.ID, using = "stateinput")
	private WebElement state;

	@FindBy(how = How.ID, using = "inputPostcode")
	private WebElement postCode;

	@FindBy(how = How.ID, using = "inputCountry")
	private WebElement country;

	@FindBy(how = How.ID, using = "customfield1")
	private WebElement howFindUsDropdown;

	@FindBy(how = How.ID, using = "customfield2")
	private WebElement mobNumber;

	@FindBy(how = How.ID, using = "inputNewPassword1")
	private WebElement password;

	@FindBy(how = How.ID, using = "inputNewPassword2")
	private WebElement confirmPassword;

	@FindBy(how = How.ID, using = "passwordStrengthTextLabel")
	private WebElement passwordStrength;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Generate Password')]")
	private WebElement generatePassword;

//	@FindBy(how = How.XPATH, using = "//span[text()='Yes']")
	private static By disableJobMailingList = By.xpath("//span[text()='Yes']");
	private static By enableJobMailingList = By.xpath("//span[text()='No']");

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'recaptcha-checkbox')]")
	private WebElement captchaCheckbox;

	@FindBy(how = How.XPATH, using = "//input[@value='Register']")
	private WebElement registerButton;

	@FindBy(how = How.XPATH, using = "//a[text()='Register']")
	private WebElement registerButtonMenu;

	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger']//following::ul")
	private WebElement errors;

	
	public RegistrationPage(TestContext context) {
		super(context);
	}

	public void enterPersonalInfo(String firstNameValue, String lastNameValue, String emailAddressValue,
			String phoneNum) {
		testContext.getElementFactory().untilPageLoadComplete();
		testContext.getElementFactory().enterTextBox(firstName, firstNameValue);
		testContext.getElementFactory().enterTextBox(lastName, lastNameValue);
		testContext.getElementFactory().enterTextBox(emailAddress, emailAddressValue);
		testContext.getElementFactory().enterTextBox(phoneNumber, phoneNum);
	}

	public void enterBillingAddress(String compName, String address1, String address2, String cityValue,
			String stateValue, String postCodeValue, String countryValue) {
		testContext.getElementFactory().enterTextBox(companyName, compName);
		testContext.getElementFactory().enterTextBox(streetAddress, address1);
		testContext.getElementFactory().enterTextBox(streetAddress2, address2);
		testContext.getElementFactory().enterTextBox(city, cityValue);
		testContext.getElementFactory().enterTextBox(postCode, postCodeValue);
		testContext.getElementFactory().elementSelectDropDown(country, countryValue);
	}

	public void enterAddReqInfo(String howFindUs, String mobNum) {
		testContext.getElementFactory().elementSelectDropDown(howFindUsDropdown, howFindUs);
		testContext.getElementFactory().enterTextBox(mobNumber, mobNum);
	}

	public void enterAccountSecurity(String pass, String confirmPass) {
		testContext.getElementFactory().enterTextBox(password, pass);
		testContext.getElementFactory().enterTextBox(confirmPassword, confirmPass);
	}

	public void disableJoinOurMailingList() {
		testContext.getElementFactory().waitUntilClickable(driver.findElement(disableJobMailingList));
		testContext.getElementFactory().javaScriptExecutorClick(driver.findElement(disableJobMailingList));
		driver.findElement(enableJobMailingList).sendKeys(Keys.TAB);
	}
	
	public void selectCaptchaCheckbox() {
		testContext.getElementFactory().elementMouseHover(captchaCheckbox, true);
		testContext.getElementFactory().waitUntilClickable(captchaCheckbox);
		testContext.getElementFactory().javaScriptExecutorClick(captchaCheckbox);
	}
	
	public void clickRegisterButton() {
		testContext.getElementFactory().elementClick(registerButton);
	}

	public void clickRegisterMenu() {
		testContext.getElementFactory().elementClick(registerButtonMenu);
	}
	
	
	public void validatePassWeakModerateStrong() {
		testContext.getElementFactory().enterTextBox(password, "jjgrh");
		assertTrue("Password strength is Weak.",
				testContext.getElementFactory().elementGetText(passwordStrength).trim().contains("Weak"));
		testContext.getElementFactory().enterTextBox(password, "jjgrh8789");
		assertTrue("Password strength is Moderate.",
				testContext.getElementFactory().elementGetText(passwordStrength).trim().contains("Moderate"));
		testContext.getElementFactory().enterTextBox(password, "jjgrh8789@&*");
		assertTrue("Password strength is Strong.",
				testContext.getElementFactory().elementGetText(passwordStrength).trim().contains("Strong"));
	}

	public void validateEmailInvalidField() {		
		assertTrue("The email address you entered was not valid error is not shown.", testContext.getElementFactory()
				.elementGetText(errors).trim().contains("The email address you entered was not valid"));
	}
	
	public void verifyMobNumMissingError() {
		assertTrue("You did not enter your phone number error is not shown.", testContext.getElementFactory()
				.elementGetText(errors).trim().contains("You did not enter your phone number"));
	}
	
}
