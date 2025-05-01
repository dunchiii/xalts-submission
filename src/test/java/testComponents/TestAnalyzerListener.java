package testComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestAnalyzerListener implements ITestListener {

	private ExtentReports extent;
	private ExtentTest test;

	@Override
	public void onStart(ITestContext context) {
		System.out.println("TEST SUITE STARTED: " + context.getName());
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/Extent-Report-Output/extent-report.html");
		reporter.config().setDocumentTitle("Xalts Automation Report");
		reporter.config().setReportName("Functional Tests Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Dhruvansh Tanwar");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIPPED: " + result.getName());
		test.skip("⚠️ Test Skipped: " + result.getMethod().getMethodName());

	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		System.out.println("STARTED: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("PASSED: " + result.getName());
		test.pass("✅ Test Passed");

		Object testInstance = result.getInstance();
		WebDriver driver = ((BaseTest) testInstance).driver;

		String screenshotPath = ScreenshotUtil.captureScreenshot(driver,
				result.getMethod().getMethodName() + "_Passed");
		try {
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("FAILED: " + result.getName());
		System.out.println("Reason: " + result.getThrowable());
		test.fail("❌ Test Failed");
		test.fail(result.getThrowable());

		Object testInstance = result.getInstance();
		WebDriver driver = ((BaseTest) testInstance).driver;

		String screenshotPath = ScreenshotUtil.captureScreenshot(driver,
				result.getMethod().getMethodName() + "_Failed");
		try {
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("TEST SUITE FINISHED: " + context.getName());
		extent.flush();
	}
}
