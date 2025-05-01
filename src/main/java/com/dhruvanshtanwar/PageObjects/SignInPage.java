package com.dhruvanshtanwar.PageObjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dhruvanshtanwar.abtractComponents.AbstractComponent;

public class SignInPage extends AbstractComponent {

	WebDriver driver;

	public SignInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='App']//div[1]//div[1]//input[1]")
	WebElement emailInput;

	@FindBy(xpath = "//label[text()='Password']/following-sibling::div//input[@type='password']")
	WebElement passInput;

	@FindBy(xpath = "//button[text()='Sign In']")
	WebElement buttonSignIn;

	@FindBy(xpath = "//button[text()='Sign Out']")
	WebElement buttonSignOut;

	@FindBy(xpath = "//button[text()='Already have an account? Click here to sign in.']")
	WebElement buttonAlreadyHaveAnAccount;

	@FindBy(xpath = "//div/label[text()='Confirm Password']/following-sibling::div/input")
	WebElement inputConfirmPassword;

	@FindBy(xpath = "//button[text()='Sign Up']")
	WebElement buttonSignUp;

	public void goToLoginWithExistingAccountScreen() throws InterruptedException {
		waitForWebElementToAppear(buttonAlreadyHaveAnAccount);
		buttonAlreadyHaveAnAccount.click();
		waitForFiveSeconds();
	}

	public void loginWithExistingAccount() throws InterruptedException {
		goToLoginWithExistingAccountScreen();
		waitForFiveSeconds();
		emailInput.isDisplayed();
		passInput.isDisplayed();
		emailInput.sendKeys("dhruvansht@gmail.com");
		passInput.sendKeys("Devilmaycry@123");
		buttonSignIn.isEnabled();
		buttonSignIn.click();
		waitForFiveSeconds();
		verifyLandedOnHomePage();

	}

	public void signUpWithNewAccount(String email, String password, String confirmPassword) {
		waitForSeconds(3);
		emailInput.sendKeys(email);
		passInput.sendKeys(password);
		inputConfirmPassword.sendKeys(confirmPassword);
		waitForSeconds(2);

		if (buttonSignUp.isEnabled() == true) {
			buttonSignUp.click();
		}
		waitForSeconds(4);
//		verifyLandedOnHomePage();
	}

	public boolean verifyLandedOnHomePage() {
		// verify heading of the landing page
		waitForElementToAppear(By.xpath("//h1[text()='Open Capital Network']"));
		return driver.findElement(By.xpath("//h1[text()='Open Capital Network']")).isDisplayed();

		// also now sign in button has now turned to signout button

	}

	public void signOutButtonIsEnabled() {
		buttonSignOut.isEnabled();
	}

	public boolean verifySingUpButtonIsDisabled() {
		return buttonSignUp.isEnabled();
	}

	public void clickOnSignUpButton() {
		buttonSignUp.click();
	}

	public String getAlertMessageAndAccept() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String alertMessage = alert.getText();
		System.out.println("Alert text: " + alertMessage);
		alert.accept();
		return alertMessage;
	}

	public void loginWithCredentials(String email, String password) throws InterruptedException {
		goToLoginWithExistingAccountScreen();
		waitForFiveSeconds();
		emailInput.isDisplayed();
		passInput.isDisplayed();
		emailInput.sendKeys(email);
		passInput.sendKeys(password);
		waitForFiveSeconds();
	}

	public boolean verifySignInButtonIsEnabled() {
		return buttonSignIn.isEnabled();
	}

	public boolean verifySignOutButtonIsEnabled() {
		return buttonSignOut.isEnabled();
	}

	public void performSignIn() {
		buttonSignIn.isEnabled();
		buttonSignIn.click();
	}

	public void performLogout() {
		waitForWebElementToAppear(buttonSignOut);
		verifySignOutButtonIsEnabled();
		waitForWebElementToAppear(buttonSignOut);
		waitForFiveSeconds();
		buttonSignOut.click();

	}

	public boolean verifyRedirectedToLoginPage() {
		buttonSignIn.isEnabled();
		return buttonSignIn.isDisplayed();
	}

	public boolean verifySignOutButtonIsVisible() {
		buttonSignOut.isEnabled();
		return buttonSignOut.isDisplayed();
	}

}
