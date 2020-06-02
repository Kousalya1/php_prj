package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProviders.EnvPropertyManager;
import pageObjects.RegistrationPage;
import utilities.TestContext;
import pageObjects.LoginPage;
import pageObjects.MyDetailsPage;

public class PhpTravelsRegistrationSteps {

	TestContext testContext;
	private LoginPage loginPage;
	private RegistrationPage registrationPage;
	private MyDetailsPage myDetailsPage;
	
	public PhpTravelsRegistrationSteps(TestContext testContext) {
		this.testContext = testContext;
		loginPage = new LoginPage(testContext);
		registrationPage = new RegistrationPage(testContext);
		myDetailsPage = new MyDetailsPage(testContext);
	}
	
	@Given("^Launch PHP Travels page and validate page header$")
	public void launch_PHP_Travels_page_and_validate_page_header() throws Throwable {
		loginPage.launchAndVerifyHeader();
	}

	@When("^Click on register button in the header$")
	public void click_on_register_button() throws Throwable {
		registrationPage.clickRegisterMenu();
	}

	@Then("^Validate password text weak, moderate, strong$")
	public void validate_password_text_weak_moderate_strong() throws Throwable {
		registrationPage.validatePassWeakModerateStrong();
	}

	@Then("^Validate and verify email invalid field$")
	public void validate_and_verify_email_invalid_field() throws Throwable {
		registrationPage.validateEmailInvalidField();
	}

	@Then("^Verify mobile number missing error$")
	public void verify_mobile_number_missing_eror() throws Throwable {
		registrationPage.verifyMobNumMissingError();
	}

	@Then("^Enter personal info firstname: \"(.*?)\" lastname: \"(.*?)\" email id: \"(.*?)\" phone num: \"(.*?)\"$")
	public void enter_personal_info_firstname_lastname_email_id_phone_num(String firstName, String lastName, String emailAddress, String phoneNum) throws Throwable {
		registrationPage.enterPersonalInfo(firstName, lastName, emailAddress, phoneNum);
	}

	@Then("^Enter billing address Company name: \"(.*?)\" AddressOne: \"(.*?)\" AddressTwo: \"(.*?)\" City: \"(.*?)\" State: \"(.*?)\" Postcode: \"(.*?)\" Country: \"(.*?)\"$")
	public void enter_billing_address_Company_name_Address_Address_City_State_Postcode_Country(String compName, String address1, String address2, 
			String city, String state, String postCode, String country) throws Throwable {
		registrationPage.enterBillingAddress(compName, address1, address2, city, state, postCode, country);
	}

	@Then("^Enter additional required info, How did you find: \"(.*?)\", Mobile num: \"(.*?)\"$")
	public void enter_additional_required_info_How_did_you_find_Mobile_num(String howFindUs, String mobNum) throws Throwable {
		registrationPage.enterAddReqInfo(howFindUs, mobNum);
	}

	@Then("^Enter account security info Password: \"(.*?)\" Confirm password: \"(.*?)\"$")
	public void enter_account_security_info_Password_Confirm_password(String password, String confirmPassword) throws Throwable {
		registrationPage.enterAccountSecurity(password, confirmPassword);
	}

	@Then("^Disable join our mailing list option$")
	public void disable_join_our_mailing_list_option () throws Throwable {
		registrationPage.disableJoinOurMailingList();
	}

	@Then("^Select the captcha checkbox$")
	public void select_the_captcha_checkbox() throws Throwable {
		registrationPage.selectCaptchaCheckbox();
	}
	
	@Then("^Click on Register button$")
	public void click_on_Register_button() throws Throwable {
		registrationPage.clickRegisterButton();
	}
	
	@When("^Enter username, password to login and ensure login success$")
	public void enter_username_and_password() throws Throwable {
		loginPage.loginAndEnsure(EnvPropertyManager.getUsername(), EnvPropertyManager.getPassword());
	}

	@Then("^Click on Logout button and ensure user logged out successfully$")
	public void click_on_Logout_button() throws Throwable {
		loginPage.logoutAndEnsure();
	}

	@When("^Navigate to my details page$")
	public void navigate_to_my_details_page() throws Throwable {
		myDetailsPage.navigateToMydetailsPage();
	}

	@Then("^Verify personal info firstname: \"(.*?)\" lastname: \"(.*?)\" email id: \"(.*?)\" phone num: \"(.*?)\"$")
	public void verify_personal_info_firstname_lastname_email_id_phone_num(String firstName, String lastName, String emailAddress, String phoneNum) throws Throwable {
		myDetailsPage.verifyPersonalInfo(firstName, lastName, emailAddress, phoneNum);
	}

	@Then("^Verify billing address Company name: \"(.*?)\" AddressOne: \"(.*?)\" AddressTwo: \"(.*?)\" City: \"(.*?)\" State: \"(.*?)\" Postcode: \"(.*?)\" Country: \"(.*?)\"$")
	public void verify_billing_address_Company_name_AddressOne_AddressTwo_City_State_Postcode_Country(String compName, String address1,
			String address2, String city, String state, String postCode, String country) throws Throwable {
		myDetailsPage.verifyBillingAddress(compName, address1, address2, city, state, postCode, country);
	}
	
	@Then("^Verify additional required info, How did you find: \"(.*?)\", Mobile num: \"(.*?)\"$")
	public void verify_additional_required_info_How_did_you_find_Mobile_num(String howFindUs, String mobNum) throws Throwable {
		myDetailsPage.verifyAddReqInfo(howFindUs, mobNum);
	}
	
	@Then("Verify job mailing list option is enabled")
	public void verify_job_mailing_list_option_is_enabled() {
		myDetailsPage.verifyJoinOurMailingListIsEnabled();
	}
	
}
