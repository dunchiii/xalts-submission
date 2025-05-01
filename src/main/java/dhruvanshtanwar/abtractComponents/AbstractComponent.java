package dhruvanshtanwar.abtractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	private WebDriver driver;

	@FindBy(xpath = "//button[text()='Sign In']")
	protected WebElement buttonSignIn;

	// button[text()='Sign Out']
	@FindBy(xpath = "//button[text()='Sign Out']")
	protected WebElement buttonSignOut;

	@FindBy(xpath = "(//button)[2]")
	protected WebElement buttonGetStarted;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) {
		// Wait for the Products to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		// Wait for the Products to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	public void signOut() {
		buttonSignOut.click();
	}

	public void signIn() {
		buttonSignIn.click();
	}

	public void waitForFiveSeconds() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void waitForSeconds(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	public void signOutButtonIsPresent() {
		buttonSignOut.isDisplayed();
	}

}
