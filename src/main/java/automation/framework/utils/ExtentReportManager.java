package automation.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportManager {
	
	    private static ExtentReports extent;

	    public static ExtentReports getExtentReport() {

	        if (extent == null) {

	            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	            String reportPath = System.getProperty("user.dir")
	                    + "/reports/ExtentReport_" + timestamp + ".html";

	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
	            sparkReporter.config().setReportName("Automation Test Report");
	            sparkReporter.config().setDocumentTitle("Selenium Hybrid Framework");

	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);

	            extent.setSystemInfo("Tester", "Akash");
	            extent.setSystemInfo("Environment", "QA");
	            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
	        }

	        return extent;
	    }
	}
