package automation.framework.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automation.framework.utils.ConfigReader;
import automation.framework.utils.ExtentReportManager;
import automation.framework.utils.ExtentTestManager;
import automation.framework.utils.ScreenshotsUtils;

import java.lang.reflect.Method;


public class BaseTest {
	
	//protected WebDriver driver;
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	protected WebDriver getDriver() {
		return driver.get();
	}
	
	protected static ExtentReports extent;
	protected ExtentTest test;
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);

	

	@BeforeMethod
	public void setUp(Method method) {
		extent = ExtentReportManager.getExtentReport();
		ExtentTest test = extent.createTest(method.getName());
		ExtentTestManager.setTest(test);

		
		String browsername=ConfigReader.getProperty("browser");

		logger.info("==========Initializing test execution===========");
		logger.info("===Launching browser===: " + browsername);
		logger.error("LOG4J TEST MESSAGE");

		
		if(browsername.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		}
		else {
			throw new RuntimeException("Invalid browsername");
		}
		//driver.get().manage().window().maximize();
		getDriver().manage().window().maximize();
		//driver.get(ConfigReader.getProperty("baseURL"));
		getDriver().get(ConfigReader.getProperty("baseURL"));
		test = extent.createTest(this.getClass().getSimpleName());

	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		ExtentTestManager.getTest().info("Closing browser");
		ExtentTestManager.getTest().info("Test execution completed");
		
		ExtentTestManager.unload();
		
		if (result.getStatus() == ITestResult.SUCCESS) {
	        test.log(Status.PASS, "Test Passed");
	    } else if (result.getStatus() == ITestResult.FAILURE) {
	        String screenshotPath =
	                ScreenshotsUtils.captureScreenshot(driver, result.getName());
	        test.fail(result.getThrowable())
	            .addScreenCaptureFromPath(screenshotPath);
	    } else if (result.getStatus() == ITestResult.SKIP) {
	        test.log(Status.SKIP, "Test Skipped");
	    }
		
		if(getDriver()!=null) {
			getDriver().quit();
			driver.remove();
		}
		extent.flush();
	}

}
