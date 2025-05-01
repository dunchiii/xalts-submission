package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dhruvanshtanwar.PageObjects.SignInPage;

import testComponents.BaseTest;

public class SignUpTest extends BaseTest {

	public SignInPage signInPageObject;
	public String alertMessageForExistingEmailSignUp = "Provided E-Mail is already in use";

	/*
	 * 1. Land on the LandingPage 2. Click on Get Started 3. Enter a combo of
	 * username and pass 4. Click on SingUp button 5. Verify user has landed on
	 * homepage 6. Sign out button is enabled
	 */
	@BeforeMethod(groups = { "Smoke", "Regression" })
	public void clickOnGetStarted() throws InterruptedException {
		super.LaunchApplication();
		this.signInPageObject = landingPageObject.getStarted();
	}

	@Test(groups = { "Smoke", "Regression" })
	public void testSignUp_WithValidCredentials_ShouldSucceed() {
		signInPageObject.signUpWithNewAccount("dhruvansht" + String.valueOf(System.currentTimeMillis()) + "@gmail.com",
				"Welcome@123", "Welcome@123");
		Assert.assertTrue(signInPageObject.verifyLandedOnHomePage());

	}

	@Test(groups = { "Regression" })
	public void testSignUp_WithInvalidEmailFormat_ShouldNotSucceed() {
		signInPageObject.signUpWithNewAccount("dhruvansht" + String.valueOf(System.currentTimeMillis()) + " ",
				"Welcome@123", "Welcome@123");
		Assert.assertFalse(signInPageObject.verifySingUpButtonIsDisabled());
	}

	@Test(groups = { "Regression" })
	public void testSignUp_WithWeakPassword_ShouldShowValidationError() {
		signInPageObject.signUpWithNewAccount("dhruvansht" + String.valueOf(System.currentTimeMillis()) + "@gmail.com",
				"Welcome123", "Welcome123");
		Assert.assertFalse(signInPageObject.verifySingUpButtonIsDisabled());
	}

	@Test(groups = { "Regression" })
	public void testSignUp_ConfirmPasswordNotMatching_ShouldNotSucceed() {
		signInPageObject.signUpWithNewAccount("dhruvansht" + String.valueOf(System.currentTimeMillis()) + "@gmail.com",
				"Welcome@123", "Welcome@1234");

		// To verify that the SignUp button is actually disabled.
		Assert.assertFalse(signInPageObject.verifySingUpButtonIsDisabled());
	}

	@Test(groups = { "Regression" })
	public void testSignUp_WithDuplicateEmail_ShouldShowAnAlert() {
		signInPageObject.signUpWithNewAccount("dhruvansht@gmail.com", "Welcome@123", "Welcome@123");
		Assert.assertEquals(signInPageObject.getAlertMessageAndAccept(), alertMessageForExistingEmailSignUp);
	}

	@Test(groups = { "Regression" })
	public void testSignUp_WithEmptyFields_ShouldNotSucceed() {
		signInPageObject.signUpWithNewAccount("", "", "");
		Assert.assertFalse(signInPageObject.verifySingUpButtonIsDisabled());
	}

}
