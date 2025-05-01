package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dhruvanshtanwar.PageObjects.SignInPage;

import testComponents.BaseTest;

public class SignInTest extends BaseTest {

	public SignInPage signInPageObject;
	public String alertMessageInvalidCredentials = "Incorrect E-Mail or Password";
	public String alertMessageInvalidUser = "User not found";

	@BeforeMethod(groups = { "Smoke", "Regression" })
	public void clickOnGetStarted() throws InterruptedException {
		super.LaunchApplication();
		this.signInPageObject = landingPageObject.getStarted();
	}

	@Test(groups = { "Smoke", "Regression" })
	public void testSignIn_WithValidCredentials_ShouldSucceed() throws InterruptedException {
		signInPageObject.loginWithCredentials("dhruvansht@gmail.com", "Devilmaycry@123");
		signInPageObject.performSignIn();
		Assert.assertTrue(signInPageObject.verifyLandedOnHomePage());
		Assert.assertTrue(signInPageObject.verifySignOutButtonIsEnabled());
	}

	@Test(groups = { "Regression" })
	public void testSignIn_WithWrongPassword_ShouldShowError() throws InterruptedException {
		signInPageObject.loginWithCredentials("dhruvansht@gmail.com", "Welcome@1231");
		signInPageObject.performSignIn();
		Assert.assertEquals(signInPageObject.getAlertMessageAndAccept(), alertMessageInvalidCredentials);
	}

	@Test(groups = { "Regression" })
	public void testSignIn_WithUnregisteredEmail_ShouldShowError() throws InterruptedException {
		signInPageObject.loginWithCredentials("unregistered" + System.currentTimeMillis() + "@gmail.com",
				"Welcome@1231");
		signInPageObject.performSignIn();
		Assert.assertEquals(signInPageObject.getAlertMessageAndAccept(), alertMessageInvalidUser);
	}

	@Test(groups = { "Regression" })
	public void testSignIn_WithEmptyFields_ShouldNotProceed() throws InterruptedException {
		signInPageObject.loginWithCredentials("", "");
		Assert.assertFalse(signInPageObject.verifySignInButtonIsEnabled());
	}
}
