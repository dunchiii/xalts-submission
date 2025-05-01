package com.dhruvanshtanwar.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dhruvanshtanwar.abtractComponents.AbstractComponent;

public class OnboardingPage extends AbstractComponent {

	WebDriver driver;

	public OnboardingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='App']//div[1]//div[1]//input[1]")
	WebElement buttonLaunchOCN;

	@FindBy(xpath = "//h2[text()='Onboard OCN Node']")
	WebElement buttonOnboardOCN;

	@FindBy(xpath = "//h2[text()='Launch OCN Child Network']")
	WebElement buttonSignIn;

	@FindBy(xpath = "//button[text()='Already have an account? Click here to sign in.']")
	WebElement buttonAlreadyHaveAnAccount;

	public void goToLoginWithExistingAccountScreen() throws InterruptedException {
		buttonAlreadyHaveAnAccount.click();
		waitForFiveSeconds();
	}

	public void onBoardOCN() throws InterruptedException {
		buttonOnboardOCN.isDisplayed();
		buttonOnboardOCN.click();
	}

	public void launchOCN() {
		// verify heading of the landing page
		buttonLaunchOCN.isDisplayed();
		buttonLaunchOCN.click();
	}

}
