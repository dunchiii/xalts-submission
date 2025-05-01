package testComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import com.dhruvanshtanwar.PageObjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPageObject;

	public LandingPage LaunchApplication() {
		driver = intializeDriver();
		this.landingPageObject = new LandingPage(driver);
		landingPageObject.launchApplication();
		return landingPageObject;
	}

	public WebDriver intializeDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
