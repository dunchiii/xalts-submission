package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dhruvanshtanwar.PageObjects.SignInPage;

import testComponents.BaseTest;

public class SignOutTest extends BaseTest {

	public SignInPage signInPageObject;

	@BeforeMethod(groups = { "Smoke", "Regression" })
	public void loginToApp() throws InterruptedException {
		super.LaunchApplication();
		this.signInPageObject = landingPageObject.getStarted();
		signInPageObject.loginWithCredentials("dhruvansht@gmail.com", "Devilmaycry@123");
		signInPageObject.performSignIn();
	}

	@Test(groups = "Smoke")
	public void testSignOut_WhenLoggedIn_ShouldRedirectToLogin() {
		signInPageObject.performLogout();
		Assert.assertTrue(signInPageObject.verifyRedirectedToLoginPage());
	}

	@Test (groups = { "Regression" })
	public void testSignOut_AccessGetStartedUrl_ShouldRedirectToHomePage() {
		signInPageObject.performLogout();
		signInPageObject.verifyLandedOnHomePage();
		// Try accessing home/dashboard after logout
		driver.get("https://xaltsocnportal.web.app/getting-started"); // Replace with actual dashboard URL
		Assert.assertTrue(signInPageObject.verifyLandedOnHomePage());
	}

	@Test(groups = { "Smoke","Regression" })
	public void testSignOut_Button_ShouldBeVisibleAfterLogin() {
		Assert.assertTrue(signInPageObject.verifySignOutButtonIsVisible());
	}
	
	
	@Test(groups = { "Regression" })
	public void testSignOut_AccessOnboardingPage_ShouldRedirectToHomePage() {
		//https://xaltsocnportal.web.app/ocn-node-onboarding
		signInPageObject.performLogout();
		signInPageObject.verifyLandedOnHomePage();
		driver.get("https://xaltsocnportal.web.app/ocn-node-onboarding"); // Replace with actual dashboard URL
		Assert.assertTrue(signInPageObject.verifyLandedOnHomePage());
	}
	
	@Test(groups = { "Regression" })
	public void testSignOut_AccessLaunchChildNetworkPage_ShouldRedirectToHomePage() {
		signInPageObject.performLogout();
		signInPageObject.verifyLandedOnHomePage();
		driver.get("https://xaltsocnportal.web.app/child-net-launch");
		Assert.assertTrue(signInPageObject.verifyLandedOnHomePage());
	}
}
