package automation.framework.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotsUtils {
	
	    public static String captureScreenshot(ThreadLocal<WebDriver> driver, String testName) {

	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String screenshotPath = System.getProperty("user.dir")
	                + "/screenshots/" + testName + "_" + timestamp + ".png";

	        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File destFile = new File(screenshotPath);

	        try {
	            FileUtils.copyFile(srcFile, destFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return screenshotPath;
	    }
	}
