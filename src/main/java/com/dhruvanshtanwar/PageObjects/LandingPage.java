package com.dhruvanshtanwar.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dhruvanshtanwar.abtractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Sign In']")
	WebElement buttonSignIn;


	public void launchApplication() {
		driver.get("https://xaltsocnportal.web.app/");
	}

	public void goToSignInScreen() {
		buttonSignIn.click();
	}

	public SignInPage getStarted() {
		// TODO Auto-generated method stub
		buttonGetStarted.click();
		return new SignInPage(driver);

	}
}
