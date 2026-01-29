package automation.framework.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import automation.framework.utils.ConfigReader;
import automation.framework.utils.ExtentReportManager;
import automation.framework.utils.ExtentTestManager;
import automation.framework.utils.ScreenshotsUtils;

import java.lang.reflect.Method;


public class BaseTest {
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	protected WebDriver getDriver(){
		return driver.get();
		}
	
	protected static ExtentReports extent;
	
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);

	@BeforeSuite
	public void setupReport() {
	    extent = ExtentReportManager.getExtentReport();
	}

	@BeforeMethod
	public void setUp(Method method) {
		
		ExtentTestManager.setTest(extent.createTest(method.getName()));
		
		String browsername=ConfigReader.getProperty("browser");

		logger.info("==========Initializing test execution===========");
		logger.info("===Launching browser===: " + browsername);
		
		if(browsername.equalsIgnoreCase("chrome")) 
		{
			driver.set(new ChromeDriver());
		}
		else if(browsername.equalsIgnoreCase("firefox")) 
		{
			driver.set(new FirefoxDriver());
		}
		else 
		{
			throw new RuntimeException("Invalid browsername");
		}
		
		getDriver().manage().window().maximize();
		getDriver().get(ConfigReader.getProperty("baseURL"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		ExtentTestManager.getTest().info("Closing browser");
		ExtentTestManager.getTest().info("Test execution completed");
		
		if (result.getStatus() == ITestResult.SUCCESS) 
			{
            	ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
        	}
        else if (result.getStatus() == ITestResult.FAILURE) 
        	{
            	String screenshotPath = ScreenshotsUtils.captureScreenshot(
            			getDriver(), result.getName());
            			ExtentTestManager.getTest()
                    		.fail(result.getThrowable())
                    		.addScreenCaptureFromPath(screenshotPath);
        	} 
        	else if (result.getStatus() == ITestResult.SKIP) 
        	{
            	ExtentTestManager.getTest()
                    .log(Status.SKIP, "Test Skipped");
        	}
		
		if(getDriver()!=null) {
			getDriver().quit();
			driver.remove();
		}
		
		ExtentTestManager.unload();
	}
	
	@AfterSuite
	public void tearDownReport() {
	    if (extent != null) {
	        extent.flush();
	    }
	}
}